---
layout: default

title: 面试

---

##面试准备

###设计模式
	装饰器的作用就是为已经存在的对象添加额外的功能
	例如，查看一个函数的执行时间，把函数装饰成有计时功能。
		def foo():  
	  
	    print 'in foo()'  
	  
	   
	  
	# 定义一个计时器，传入一个，并返回另一个附加了计时功能的方法  
	  
	def timeit(func):  
	  
	       
	  
	    # 定义一个内嵌的包装函数，给传入的函数加上计时功能的包装  
	  
	    def wrapper():  
	  
	        start = time.clock()  
	  
	        func()  
	  
	        end =time.clock()  
	  
	        print 'used:', end - start  
	  
	       
	  
	    # 将包装后的函数返回  
	  
	    return wrapper  
	  
	   
	  
	foo = timeit(foo)  
	  
	foo()  

###php

###docker

###python

###ios

熟悉CocoaTouch，CoreData，Runtime，对iOS下的并行开发，网络管理，数据存储，内存管理，GUI开发，消息通讯机制，安全机制有自己的深入体会； 

	http://www.swifthumb.com/thread-11284-1-1.html

###服务端框架
	yii
	
###javascript

Javascript是一种基于对象（object-based）的语言，你遇到的所有东西几乎都是对象。但是，它又不是一种真正的面向对象编程（OOP）语言，因为它的语法中没有class

Javascript语言不支持"类"，但是可以用一些变通的方法，模拟出"类"。
一、构造函数法

它用构造函数模拟"类"，在其内部用this关键字指代实例对象

	function Cat() {
		this.name = "大毛";
	}
	
	
	var cat1 = new Cat();
	alert(cat1.name); // 大毛

二、Object.create()法

	var Cat = {
	　　　　name: "大毛",
	　　　　makeSound: function(){ alert("喵喵喵"); }
	　　};
	var cat1 = Object.create(Cat);
	alert(cat1.name); // 大毛
	cat1.makeSound(); // 喵喵喵

三、极简主义法

	　　var Cat = {
	　　　　createNew: function(){
	　　　　　　var cat = {};
	　　　　　　cat.name = "大毛";
	　　　　　　cat.makeSound = function(){ alert("喵喵喵"); };
	　　　　　　return cat;
	　　　　}
	　　};
	　　var cat1 = Cat.createNew();
	　　cat1.makeSound(); // 喵喵喵
	　
	　

javascript Prototype

	Javascript规定，每一个构造函数都有一个prototype属性，指向另一个对象。这个对象的所有属性和方法，都会被构造函数的实例继承。
	这意味着，我们可以把那些不变的属性和方法，直接定义在prototype对象上。
	　　function Cat(name,color){
	　　　　this.name = name;
	　　　　this.color = color;
	　　}
	　　Cat.prototype.type = "猫科动物";
	　　Cat.prototype.eat = function(){alert("吃老鼠")};
	然后，生成实例。
	　　var cat1 = new Cat("大毛","黄色");
	　　var cat2 = new Cat("二毛","黑色");
	　　alert(cat1.type); // 猫科动物
	　　cat1.eat(); // 吃老鼠
　　
　　　
###前端
	最近在看哪些前端方面的书？
	
	js的基本数据类型有哪些？
	
	--字符串、数字、布尔、数组、对象、Null、Undefined
	
	解释下什么是闭包？
	
	--全局变量、局部变量
	--闭包就是能够读取其他函数内部变量的函数
	---http://www.ruanyifeng.com/blog/2009/08/learning_javascript_closures.html
	
	CSS常用的样式有哪些？
	
	了解CSS3么？
	CSS3 用于控制网页的样式和布局
	选择器
	盒模型
	背景和边框
	文字特效
	2D/3D转换
	动画
	多列布局
	用户界面


	知道哪些HTML5的新特性？
	
	
	1. 新的Doctype 
	尽管使用<!DOCTYPE html>，即使浏览器不懂这句话也会按照标准模式去渲染 
	2. Figure元素 
	用<figure>和<figcaption>来语义化地表示带标题的图片 
	<figure> 
	<img src=”path/to/image” alt=”About image” /> 
	<figcaption> 
	<p>This is an image of something interesting. </p> 
	</figcaption> 
	</figure> 
	3. 重新定义的<small> 
	<small>已经被重新定义了，现在被用来表示小的排版，如网站底部的版权声明 
	4. 去掉link和script标签里面的type属性 
	5. 加/不加 括号 
	HTML5没有严格的要求属性必须加引号，闭合不闭合，但是建议加上引号和闭合标签 
	6. 让你的内容可编辑，只需要加一个contenteditable属性 
	7. Email Inputs 
	如果我们给Input的type设置为email，浏览器就会验证这个输入是否是email类型，当然不能只依赖前端的校验，后端也得有相应的校验 
	8. Placeholders 
	这个input属性的意义就是不必通过javascript来做placeholder的效果了 
	9. Local Storage 
	使用Local Storage可以永久存储大的数据片段在客户端（除非主动删除），目前大部分浏览器已经支持，在使用之前可以检测一下window.localStorage是否存在 
	10. 语义化的header和footer 
	11. 更多的HTML5表单特性 
	12. IE和HTML5 
	默认的，HTML5新元素被以inline的方式渲染，不过可以通过下面这种方式让 
	其以block方式渲染 
	header, footer, article, section, nav, menu, hgroup { 
	display: block; 
	} 
	不幸的是IE会忽略这些样式，可以像下面这样fix: 
	document.createElement(”article”); 
	document.createElement(”footer”); 
	document.createElement(”header”); 
	document.createElement(”hgroup”); 
	document.createElement(”nav”); 
	document.createElement(”menu”); 
	13. hgroup 
	一般在header里面用来将一组标题组合在一起，如 
	<header> 
	<hgroup> 
	<h1> Recall Fan Page </h1> 
	<h2> Only for people who want the memory of a lifetime. </h2> 
	</hgroup> 
	</header> 
	14. Required属性 
	required属性定义了一个input是否是必须的，你可以像下面这样声明 
	<input type=”text” name=”someInput” required> 
	或者 
	<input type=”text” name=”someInput” required=”required”> 
	15. Autofocus属性 
	正如它的词义，就是聚焦到输入框里面 
	<input type=”text” name=”someInput” placeholder=”Douglas Quaid” required autofocus> 
	16. Audio支持 
	HTML5提供了<audio>标签，你不需要再按照第三方插件来渲染音频，大多数现代浏览器提供了对于HTML5 Audio的支持，不过目前仍旧需要提供一些兼容处理，如 
	<audio autoplay=”autoplay” controls=”controls”> 
	<source src=”file.ogg” /><!–FF–> 
	<source src=”file.mp3″ /><!–Webkit–> 
	<a href=”file.mp3″>Download this file.</a> 
	</audio> 
	17. Video支持 
	和Audio很像，<video>标签提供了对于video的支持，由于HTML5文档并没有给video指定一个特定的编码，所以浏 览器去决定要支持哪些编码，导致了很多不一致。Safari和IE支持H.264编码的格式，Firefox和Opera支持Theora和Vorbis 编码的格式，当使用HTML5 video的时候，你必须都提供： 
	<video controls preload> 
	<source src=”cohagenPhoneCall.ogv” type=”video/ogg; codecs=’vorbis, theora’” /> 
	<source src=”cohagenPhoneCall.mp4″ type=”video/mp4; ’codecs=’avc1.42E01E, mp4a.40.2′” /> 
	<p> Your browser is old. <a href=”cohagenPhoneCall.mp4″>Download this video instead.</a> </p> 
	</video> 
	18. 预加载视频 
	preload属性就像它的字面意思那么简单，你需要决定是否需要在页面加载的时候去预加载视频 
	<video preload> 
	19. 显示视频控制 
	<video preload controls> 
	20. 正则表达式 
	由于pattern属性，我们可以在你的markup里面直接使用正则表达式了 
	<form action=”" method=”post”> 
	<label for=”username”>Create a Username: </label> 
	<input type=”text” name=”username” id=”username” placeholder=”4 <> 10″ pattern=”[A-Za-z]{4,10}” autofocus required> 
	<button type=”submit”>Go </button> 
	</form> 
	21. 检测属性支持 
	除了Modernizr之外我们还可以通过javascript简单地检测一些属性是否支持，如： 
	<script> 
	if (!’pattern’ in document.createElement(’input’) ) { 
	// do client/server side validation 
	} 
	</script> 
	22. Mark元素 
	把<mark>元素看做是高亮的作用，当我选择一段文字的时候，javascript对于HTML的markup效果应该是这样的： 
	<h3> Search Results </h3> 
	<p> They were interrupted, just after Quato said, <mark>”Open your Mind”</mark>. </p> 
	23. 什么时候用<div> 
	HTML5已经引入了这么多元素，那么div我们还要用吗？div你可以在没有更好的元素的时候去用。 
	24. 想立即使用HTML5? 
	不要等2022了，现在就可以使用了，just do it. 
	25. 哪些不是HTML5 
	1)SVG 
	2)CSS3 
	3)Geolocation 
	4)Client Storage 
	5)Web Sockets 
	26. Data属性 
	<div id=”myDiv” data-custom-attr=”My Value”> Bla Bla </div> 
	CSS中使用： 
	<style> 
	h1:hover:after { 
	content: attr(data-hover-response); 
	color: black; 
	position: absolute; 
	left: 0; 
	} 
	</style> 
	<h1 data-hover-response=”I Said Don’t Touch Me!”> Don’t Touch Me </h1> 
	27. Output元素 
	<output>元素用来显示计算结果，也有一个和label一样的for属性 
	28. 用Range Input来创建滑块 
	HTML5引用的range类型可以创建滑块，它接受min, max, step和value属性 
	可以使用css的:before和:after来显示min和max的值 
	<input type=”range” name=”range” min=”0″ max=”10″ step=”1″ value=”"> 
	input[type=range]:before { content: attr(min); padding-right: 5px; 
	} 
	input[type=range]:after { content: attr(max); padding-left: 5px;} 

	了解AJAX么，如何实现跨域？
	
	使用Javascript向服务器提出请求并处理响应而不阻塞用户！核心对象XMLHTTPRequest。通过这个对象，您的 JavaScript 可在不重载页面的情况与Web服务器交换数据，即在不需要刷新页面的情况下，就可以产生局部刷新的效果。
	
	跨域:
	浏览器对于javascript的同源策略的限制,例如a.cn下面的js不能调用b.cn中的js,对象或数据(因为a.cn和b.cn是不同域),所以跨域就出现了.
	
	上面提到的,同域的概念又是什么呢??? 简单的解释就是相同域名,端口相同,协议相同
	
	基于script标签实现跨域
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<script type="text/javascript">
	    var message = function(data) {
	        alert(data[1].title);
	    };
	</script>
	
	<script type="text/javascript" src="http://web.cn/js/message.js"></script>
	</head>
	<body>
	<div id='testdiv'></div>
	</body>
	</html>

		message([
     {"id":"1", "title":"天津新闻联播，雷人搞笑的男主持人"},
     {"id":"2", "title":"楼市告别富得流油 专家:房价下跌是大概率事件"},
     {"id":"3", "title":"法国人关注时事 八成年轻人每天阅读新闻"},
     {"id":"4", "title":"新闻中的历史,历史中的新闻"},
     {"id":"5", "title":"东阳新闻20140222"},
     {"id":"6", "title":"23个职能部门要增加新闻发布频次"},
     {"id":"7", "title":"《贵州新闻联播》 中国美丽乡村"},
     {"id":"8", "title":"朝韩离散家属团聚首轮活动结束"},
     {"id":"9", "title":"索契冬奥会一天曝出两例兴奋剂事件"},
     {"id":"10", "title":"今天中国多地仍将出现中度霾"}
 ]);

	基于jquery跨域
	
	$(document).ready(function(){
	   var url='http://localhost:8080/WorkGroupManagment/open/getGroupById"
	       +"?id=1&callback=?';
	   $.ajax({
	     url:url,
	     dataType:'jsonp',
	     processData: false, 
	     type:'get',
	     success:function(data){
	       alert(data.name);
	     },
	     error:function(XMLHttpRequest, textStatus, errorThrown) {
	       alert(XMLHttpRequest.status);
	       alert(XMLHttpRequest.readyState);
	       alert(textStatus);
	     }});
	   });    
    
    那么如何用jquery来实现我们的跨域呢???jquery已经把跨域封装到ajax上了,而且封装得非常的好,使用起来也特别方便
    
    通过iframe来跨子域
    基于iframe实现的跨域要求两个域具有aa.xx.com,bb.xx.com 这种特点，

	也就是两个页面必须属于一个基础域（例如都是xxx.com)，使用同一协议和同一端口，这样在两个页面中同时添加document.domain，就可以实现父页面调用子页面的函数
	
	要点就是 :通过修改document.domain来跨子域



	对jQuery了解多少？看过源码么？
	javascript 库
	在浏览器中输入域名到显示，其背后是怎样的一系列流程？
	
	如何优化页面的显示速度？
	
	了解数据结构么？常用的数据结构有哪些？
	
	用C语言的数组实现一个队列(手撕代码)
	
	简单介绍一下双向循环链表。
	
	介绍一下树的前中后三种遍历方式。
	
	简单说一下最短路径算法。
	
	随便列举出五个状态码。
	
	讲述一下域名到页面的过程。
	
	HTTP方法除了GET和POST还有哪些？
	
	GET请求和POST请求有长度限制么？
	
	头信息中与HTTP缓存有关的信息有哪些？
	
	CSS中的position属性有哪些值？
	
	如何水平垂直居中一个div？
	
	解释闭包。
	
	哪些操作会造成内存泄漏？
	
	前端的安全性问题有哪些？
	
	JS实现继承有哪几种方法？
	
	有涉及后端的项目么？
	
	了解React、Angular、Node么？ 
	
	Canvas画饼状图(个人项目)
	
	js如何实现定时器？简述定时器原理。
	
	左固定右自适应的两列布局(手撕)
	
	了解HTTP协议么?
	
	为什么HTTP协议基于TCP协议？TCP与UDP的区别。
	
	HTTPS和HTTP的端口号各是多少？HTTPS中的S是什么？
	
	了解跨域么？详细讲述一下JSONP的原理。
	
	简单实现一个原型继承(手撕)
	
	实现一个方法，输入为若干个数组，合并、去重、排序并返回(手撕)
	
	写一个快速排序算法(手撕)
	
	前端学习多久了？   
	
	SQL语句(笔试题)
	
	jQuery的选择器有哪些？
	
	了解Angular么？
	
	常用的排序算法有哪些？哪些是稳定的？
	
	项目吹牛
	
	SQL语句(笔试题)
	
	对jQuery熟悉么？
	
	介绍一下Node
	
	项目吹牛
	
	未来个人职业发展
	
	优秀的前端页面是怎样的？
	
	现场看项目提问细节 Canvas如何实现雷达图。
	项目吹牛
	
	display有哪些属性值？
	
	什么是inline-block，inline-block的间隙问题。
	
	行内元素float:left后是否变为块级元素？
	
	如果想隐藏页面中的一段文字，有哪些方法可以实现？
	
	Z-index可以设置为负数么？设为负数有什么用？
	
	调出了笔试时中的一道算法题，讲解思路。
	
	实现一个效果，当鼠标移动，在移动的路径上有从小到大变化后消失的心形动画。简述实现的思路。
	
	了解SVG么？Canvas和SVG有什么区别？
	
	Cookies有什么用？
	
	了解CSS预处理么？

	js高级程序设计
	
	
	
	《CSS网站布局实录》——国产CSS2入门书，有些技巧已经淘汰，但仍不失为最好的CSS入门教程。
	《无懈可击的Web设计》——讲CSS应用技巧的书，国内外粉丝别多，说是开创了CSS技巧流派也不为过。
	《DOM JavaScript编程艺术——JavaScript最好的入门书，没有之一，这本书是帮助你了解如何将DOM、CSS和JavaScript连接起来的一本书。严格来说，后端Node根本不算JavaScript，JavaScript是基于ES语法的一门脱水语言，如何实现的胶水？这本书将带你入门。
	《JavaScript高级程序设计》 ——JavaScript必读的一本精典，读完之后对JavaScript的理解和实 
	践会上升非常大的一个台阶。
	《编写高质量代码——Web前端开发修炼之道》 —— 举贤不避亲，这本书是我写的。推荐的原因是，这本书重点讲团队合作的注意事项。虽然一些具体的技巧，在今天已然过时，比如IE6的hack，但在团队合作方面的思考，直到今天我也没看到其他书在讲，这些思想没有其他书可替代。
	《HTML5和CSS3权威指南》——目前为止，我读过的HTML5方面最好的一本原创书。配合实例进行API讲解，非常详细具体。连HTML5都提供了哪些底层的东西都不知道，又该如何去用好它呢？在我看来，是学习HTML5的必读书。
	《响应式Web设计：HTML5和CSS3实战》——作者是《无懈可击的Web设计》忠实粉丝，所以很自然地，这也是本CSS技巧流派的书，侧重点在CSS3的实践技巧上，让人大开眼界。
	《JavaScript设计模式》——JavaScript在实战时的高级技巧。
	
		未来几年内，前端一定将三分天下：其一，是以Ext为代表js库将传统页面的表现形式发挥到极至;其二，Adobe的flash/flex凭着成熟的技术和丰富的经验，以及不断创新的热情，引领着前端的技术走向；其三，微软的sliverlight凭借着公司强大的技术团队和.net体系协同做战的优势，奋起直追，很有可能追赶上flash/flex．
	
	