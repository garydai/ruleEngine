---
layout: default

title: javascript笔记

---

##javascript笔记


简单的脚本语言

###对象

http://www.cnblogs.com/dolphinX/p/3286177.html

JavaScript 是面向对象的语言，但 JavaScript 不使用类。

在 JavaScript 中，不会创建类，也不会通过类来创建对象（就像在其他面向对象的语言中那样）。

JavaScript 基于 prototype，而不是基于类的。


每个函数都有一个prototype属性，这个属性是指向一个对象的引用，这个对象称为原型对象，原型对象包含函数实例共享的方法和属性，也就是说将函数用作构造函数调用（使用new操作符调用）的时候，新创建的对象会从原型对象上继承属性和方法。




创建对象

	1.
	创建直接的实例
	这个例子创建了对象的一个新实例，并向其添加了四个属性：
	实例
	person=new Object();
	person.firstname="Bill";
	person.lastname="Gates";
	person.age=56;
	person.eyecolor="blue";
	
	2.
	使用对象构造器
	本例使用函数来构造对象：
	实例
	function person(firstname,lastname,age,eyecolor)
	{
	this.firstname=firstname;
	this.lastname=lastname;
	this.age=age;
	this.eyecolor=eyecolor;
	}
	var myFather=new person("Bill","Gates",56,"blue");
	
私有变量、函数


	function Obj(){
	                var a=0; //私有变量
	                var fn=function(){ //私有函数
	                    
	                }
	            }
    
    
    
            
                    
静态变量、函数

	function Obj(){
	                
	            }
	            
	            Obj.a=0; //静态变量
	            
	            Obj.fn=function(){ //静态函数
	                    
	            }
            
实例变量、函数


	function Obj(){
	                this.a=[]; //实例变量
	                this.fn=function(){ //实例方法
	                    
	                }
	            }
            
如果函数里有定义函数，不同实例，会有多个相同函数，但多个函数内容都是一样的，应只保留一个函数，引入prototype。prototype维护一个函数列表，函数里有prototype属性指向这个prototype。有点像C++里的虚函数概念，类里面有虚函数指针指向虚函数表，表里都是虚函数地址。


###prototype

http://www.cnblogs.com/dolphinX/p/3286177.html

无论什么时候，只要创建了一个新函数，就会根据一组特定的规则为该函数创建一个prototype属性，prototype里有一个构造函数constructor，指向的是该函数。

如果新建一个实例，实例会生成一个__proto__属性，指向的是函数的prototype.

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/javascript-prototype.png)

###双向绑定


	双向数据绑定指的是将对象属性变化绑定到UI

####双向绑定实现
#####观察者设计模式

订阅者在发布者那里注册回调函数，当发布者发生变化，则调用这些函数

	function PubSub(){  
	    var pubSub = {
	        callbacks: {},
	        on: function(msg,callback) {
	            this.callbacks[msg] = this.callbacks[msg] || [];
	            this.callbacks[msg].push(callback);
	        },
	        publish: function(msg) {
	            this.callbacks[msg] = this.callbacks[msg] || [];
	            for (var i = 0,len = this.callbacks[msg].length; i < len; i++) {
	                this.callbacks[msg][i].apply(this,arguments);
	            };
	        }
	      }
	      return pubSub;
	}
	
	var writer = new PubSub();  
	var reader = {  
	    read: function(){alert("我读了这本新书")}
	};
	writer.on("newbook", function(){reader.read()});  
	writer.publish("newbook");  



#####利用观察者模式实现


	function DataBinder(object_id){
	  // 创建一个简单的pubSub对象
	  var pubSub = {
	    callbacks: {},
	    on: function(msg,callback) {
	      this.callbacks[msg] = this.callbacks[msg] || [];
	      this.callbacks[msg].push(callback);
	    },
	    publish: function(msg) {
	      this.callbacks[msg] = this.callbacks[msg] || [];
	      for (var i = 0,len = this.callbacks[msg].length; i < len; i++) {
	        this.callbacks[msg][i].apply(this,arguments);
	      };
	    }
	  },
	
	      data_attr = "data-bind-" + object_id,
	      message   = object_id + ":change",
	
	      changeHandler = function(event) {
	        var target    = event.target || event.srcElement, // IE8兼容
	            prop_name = target.getAttribute(data_attr);
	
	        if (prop_name && prop_name !== "") {
	          pubSub.publish(message,prop_name,target.value);
	        }
	      };
	
	  // 监听事件变化，并代理到pubSub
	  if (document.addEventListener) {
	    document.addEventListener("keyup",changeHandler,false);
	  } else{
	    // IE8使用attachEvent而不是addEventListenter
	    document.attachEvent("onkeyup",changeHandler);
	  };
	
	  // pubSub将变化传播到所有绑定元素
	  pubSub.on(message,function(event,prop_name,new_val){
	    var elements = document.querySelectorAll("[" + data_attr + "=" +prop_name + "]"),
	        tag_name;
	    for (var i = 0,len = elements.length; i < len; i++) {
	      tag_name = elements[i].tagName.toLowerCase();
	      console.log(tag_name);
	      if (tag_name === "input" || tag_name === "textarea" || tag_name === "select") {
	        elements[i].value = new_val;
	      } else{
	        elements[i].innerHTML = new_val;
	      };
	      console.log("prop_name:"+new_val);
	    };
	  })
	
	  return pubSub;
	}
	
	function User(uid) {
	  var binder = new DataBinder(uid),
	      user   = {
	        attribute : {},
	
	        // 属性设置器使用数据绑定器pubSub来发布
	        set : function(attr_name,val) {
	          this.attribute[attr_name] = val;
	          binder.publish(uid + ":change",attr_name,val,this);
	        },
	
	        get : function(attr_name) {
	          return this.attribute[attr_name];
	        },
	
	        _binder : binder
	      };
	
	  binder.on(uid + ":change",function(event,attr_name,new_val,initiator) {
	    if (initiator !== user) {
	      user.set(attr_name,new_val);
	    }
	  });
	
	  return user;
	}
	var user = new User( 123 );
	user.set( "name", "lwl" );
	
	
	html:
	
	<input type="text" data-bind-123="name" />
	<p data-bind-123="name"></p>

ref: http://codepen.io/anon/pen/mRdLYd?editors=1010






