---
layout: default
title: 设计模式

---

## 设计模式

1.reactor

twisted框架有使用该模式

等待事件，处理事件

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/reactor.png)

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/reactor2.png)

2.工厂模式

工厂方法模式

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/factory-method.png)

定义了一个创建对象的接口，由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。

一个抽象工厂类，多个具体工厂类，一个抽象产品类，多个具体产品类；具体工厂类生产产品

将生产产品的行为由子类执行

把产品抽象

3.抽象工厂模式

提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。

加入抽象工厂

工厂方法模式：创建抽象工厂类，具体工厂类，实例化具体工厂，创建产品

	// abstract factory
	abstract class Kitchen {
	  public abstract KitchenMeal getMeal(String preferency);
	  public abstract KitchenMeal getDessert(String preferency);
	}
	
	// concrete factory
	class KitchenFactory extends Kitchen {
	  @Override
	  public KitchenMeal getMeal(String preferency) {
	    if (preferency.equals("F.1")) {
	      return new FastFoodMeal();
	    } else if (preferency.equals("P.1")) {
	      return new ProteinMeal();
	    }
	    return new VegetarianMeal();
	  }
	 
	  @Override
	  public KitchenMeal getDessert(String preferency) {
	    if (preferency.equals("I.1")) {
	      return new IceCreamMeal();
	    }
	    return null;
	  }
	}
	 
	// abstract product
	abstract class KitchenMeal {
	  public abstract String getName();
	}
	 
	// concrete products
	class ProteinMeal extends KitchenMeal {
	  @Override
	  public String getName() {
	    return "protein meal";
	  }
	}


4.单例模式

5.建造者模式

工厂模式创建一个产品，建造者模式创建多个产品合为一起

		class Programmer {
		  private String firstName;
		  private String lastName;
		   
		  private Programmer(String fName, String lName) {
		    this.firstName = fName;
		    this.lastName = lName;
		  }
		   
		  public static class ProgrammerBuilder {
		    private String firstName;
		    private String lastName;
		     
		    public ProgrammerBuilder setFirstName(String firstName) {
		      this.firstName = firstName;
		      return this;
		    }
		     
		    public ProgrammerBuilder setLastName(String lastName) {
		      this.lastName = lastName;
		      return this;
		    }	
	
			public Programmer build() {
				return	new Programmer(this.firstName, this.lastName);
			}	     
		}


6.原型模式

对象拷贝

7.适配器模式

将一个类通过适配器变成满足另一个接口的类

对象适配器
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/adapter.png)

springmvc使用handlerAdpter，根据handler类型，使用确定使用哪个适配器来处理请求

三种handler类型

1.Controller

org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter

2.方法handlerMethod

org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

3.Servlet

org.springframework.web.servlet.handler.SimpleServletHandlerAdapter


8.代理模式

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/proxy.png)

委托人委托代理人执行事务，代理人也要反馈结果。

委托人声明协议方法，代理人需要实现该协议的方法。


	abstract class Subject
	{
	    public abstract void Request();
	}


	class RealSubject : Subject  
	{  
	    public override void Request()  
	    {  
	        //业务方法具体实现代码  
	    }  
	}  
	
	class Proxy : Subject
	{
	    private RealSubject realSubject = new RealSubject(); //维持一个对真实主题对象的引用
	
	    public void PreRequest() 
	    {
	        …...
	    }
	
	    public override void Request() 
	    {
	        PreRequest();
	        realSubject.Request(); //调用真实主题对象的方法
	        PostRequest();
	    }
	
	    public void PostRequest() 
	    {
	        ……
	    }
	}


	Interface Hello {
		void say();
	}
	
	class HelloImpl implement Hello {
		void say() {
	
		}
	}
	
	class Proxy implement Hello {
		Hello hello;
		public proxy(Hello h) {
			hello = h;
		}
	
		void say() {
			xxx;
			hello.say();
			xxx;
		}
	}
	
	Proxy p = new Proxy(new HelloImpl());
	p.say();
	
	代理类和被代理类继承同一个接口
	
	上面是静态代理，编译后就有.class文件，动态代理类的字节码在程序运行时由Java反射机制动态生成


![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/dynamic_proxy.png)

动态代理：根据接口，动态生成代理类。

        pulbic class Test implement InvocationHandler {
            private Object o;
            
            public Test(Object t) {
                this.o = t;
            }
            
            pulbic Object invoke(Object proxy, Method method, Object[] arg) {
                xxx
                method.invode(o, arg);
                xxx
            
            }
        }


​        
​        InvocationHandler test = new Test(new HelloImpl());
​        
        Hello dynamicProxy = (Hello) Proxy.newProxyInstance(实现类.class.getClassLoader(),
                        实现类.class.getInterfaces(), InvocationHandler实例);
        
        dynamicProxy.say();


9.外观模式

组合各种类

10.桥接模式

使得不同的对象使用自身的方法

11.组合模式

12.享元模式

13.策略模式
	
	 interface IStrategy {
	        public void doSomething();
	    }
	    class ConcreteStrategy1 implements IStrategy {
	        public void doSomething() {
	            System.out.println("具体策略1");
	        }
	    }
	    class ConcreteStrategy2 implements IStrategy {
	        public void doSomething() {
	            System.out.println("具体策略2");
	        }
	    }
	    class Context {
	        private IStrategy strategy;
	
	        public Context(IStrategy strategy){
	            this.strategy = strategy;
	        }
	
	        public void execute(){
	            strategy.doSomething();
	        }
	    }
	
	    public class Client {
	        public static void main(String[] args){
	            Context context;
	            System.out.println("-----执行策略1-----");
	            context = new Context(new ConcreteStrategy1());
	            context.execute();
	
	            System.out.println("-----执行策略2-----");
	            context = new Context(new ConcreteStrategy2());
	            context.execute();
	        }
	    }


14.模板方法模式

父类定义逻辑顺序，子类实现具体逻辑

15.观察者模式

把观察者放入对象内

多个观察者对象监听某对象，当该对象状态发生变化，则会通知所有观察者。

		public abstract class Subject {
		    /**
		     * 用来保存注册的观察者对象
		     */
		    private    List<Observer> list = new ArrayList<Observer>();
		    /**
		     * 注册观察者对象
		     * @param observer    观察者对象
		     */
		    public void attach(Observer observer){
		        
		        list.add(observer);
		        System.out.println("Attached an observer");
		    }
		    /**
		     * 删除观察者对象
		     * @param observer    观察者对象
		     */
		    public void detach(Observer observer){
		        
		        list.remove(observer);
		    }
		    /**
		     * 通知所有注册的观察者对象
		     */
		    public void nodifyObservers(String newState){
		        
		        for(Observer observer : list){
		            observer.update(newState);
		        }
		    }
		}


​		
​		
​		public class ConcreteSubject extends Subject{
​		    
		    private String state;
		    
		    public String getState() {
		        return state;
		    }
		
		    public void change(String newState){
		        state = newState;
		        System.out.println("主题状态为：" + state);
		        //状态发生改变，通知各个观察者
		        this.nodifyObservers(state);
		    }
		}


		public interface Observer {
		    /**
		     * 更新接口
		     * @param state    更新的状态
		     */
		    public void update(String state);
		}
	
		public class ConcreteObserver implements Observer {
		    //观察者的状态
		    private String observerState;
		    
		    @Override
		    public void update(String state) {
		        /**
		         * 更新观察者的状态，使其与目标的状态保持一致
		         */
		        observerState = state;
		        System.out.println("状态为："+observerState);
		    }
		
		}


观察类放入被观察类中，被观察类发生改变通知观察类做出反应


![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/observer.png)

16.迭代子模式

迭代器iterator

17.责任链模式

每个对象持有对下一个对象的引用

18.命令模式

把方法调用封装起来

将请求封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。命令模式也支持撤销。

命令对象把动作和接受者包进对象

客户、接受者、调用者

例子：工作队列

```
public interface Command {
	void execute();
}

public class ConcreteCommand implements Command {
	public void execute() {

	}
}

public class Invoker {
	Command command;
	public void setCommand(Command commandPara) {
		command = commandPara;
	}

	public void action {
		command.execute();
	}
}

public class client {
	public static void main() {
		Command c = new ConcreteCommand();
		Invoker i = new Invoker();
		i.setCommand(c);
		i.action();
	}
}
```
19.备忘录模式

20.状态模式

	public interface State {
   		public void doAction(Context context);
	}
	
	public class StartState implements State {
	
	   public void doAction(Context context) {
	      System.out.println("Player is in start state");
	      context.setState(this);    
	   }
	
	   public String toString(){
	      return "Start State";
	   }
	}
	
	public class StopState implements State {
	
	   public void doAction(Context context) {
	      System.out.println("Player is in stop state");
	      context.setState(this);    
	   }
	
	   public String toString(){
	      return "Stop State";
	   }
	}


​	
​	public class Context {
​	   private State state;
​	
	   public Context(){
	      state = null;
	   }
	
	   public void setState(State state){
	      this.state = state;        
	   }
	
	   public State getState(){
	      return state;
	   }
	}


	public class StatePatternDemo {
	   public static void main(String[] args) {
	      Context context = new Context();
	
	      StartState startState = new StartState();
	      startState.doAction(context);
	
	      System.out.println(context.getState().toString());
	
	      StopState stopState = new StopState();
	      stopState.doAction(context);
	
	      System.out.println(context.getState().toString());
	   }
	}



21.访问者模式

数据和操作解耦

	abstract class Element {  
	    public abstract void accept(IVisitor visitor);  
	    public abstract void doSomething();  
	}  
	  
	interface IVisitor {  
	    public void visit(ConcreteElement1 el1);  
	    public void visit(ConcreteElement2 el2);  
	}  
	  
	class ConcreteElement1 extends Element {  
	    public void doSomething(){  
	        System.out.println("这是元素1");  
	    }  
	      
	    public void accept(IVisitor visitor) {  
	        visitor.visit(this);  
	    }  
	}  
	  
	class ConcreteElement2 extends Element {  
	    public void doSomething(){  
	        System.out.println("这是元素2");  
	    }  
	      
	    public void accept(IVisitor visitor) {  
	        visitor.visit(this);  
	    }  
	}  
	class Visitor implements IVisitor {  
	  
	    public void visit(ConcreteElement1 el1) {  
	        el1.doSomething();  
	    }  
	      
	    public void visit(ConcreteElement2 el2) {  
	        el2.doSomething();  
	    }  
	}  
	  
	class ObjectStruture {  
	    public static List<Element> getList(){  
	        List<Element> list = new ArrayList<Element>();  
	        Random ran = new Random();  
	        for(int i=0; i<10; i++){  
	            int a = ran.nextInt(100);  
	            if(a>50){  
	                list.add(new ConcreteElement1());  
	            }else{  
	                list.add(new ConcreteElement2());  
	            }  
	        }  
	        return list;  
	    }  
	}  
	  
	public class Client {  
	    public static void main(String[] args){  
	        List<Element> list = ObjectStruture.getList();  
	        for(Element e: list){  
	            e.accept(new Visitor());  
	        }  
	    }  
	}  

22.中介者模式

23.解释器模式
	
	abstract class AbstractExpression {
	       public  abstract void interpret(Context ctx);
	}
	
	class TerminalExpression extends  AbstractExpression {
	   public  void interpret(Context ctx) {
	          //终结符表达式的解释操作
	   }
	}


24.装饰器模式

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/decorator.png)
咖啡是一种饮料，咖啡的本质是咖啡豆+水磨出来的。咖啡店现在要卖各种口味的咖啡，如果不使用装饰模式，那么在销售系统中，各种不一样的咖啡都要产生一个类，如果有4中咖啡豆，5种口味，那么将要产生至少20个类（不包括混合口味），非常麻烦。使用了装饰模式，只需要11个类即可生产任意口味咖啡（包括混合口味


		public abstract class AbstractCellPhone
		{
		        public abstract string CallNumber();
		        public abstract string SendMessage();
		}
		
		public class NokiaPhone : AbstractCellPhone
	   {
	        public override string CallNumber()
	        {
	            return "NokiaPhone call sombody";
	        }
	 
	        public override string SendMessage()
	        {
	            return "NokiaPhone send a message to somebody";
	        }
	   }

   


	   public abstract class Decorator : AbstractCellPhone
	    {
	        AbstractCellPhone _phone;
	 
	        public Decorator(AbstractCellPhone phone)
	        {
	            _phone = phone;
	        }
	 
	        public override string CallNumber()
	        {
	            return _phone.CallNumber();
	        }
	 
	        public override string SendMessage()
	        {
	            return _phone.SendMessage();
	        }
	  }


​	 		 

	 public class DecoratorGPS : Decorator
	 {
	   public DecoratorGPS(AbstractCellPhone phone) : base(phone){ }
	   
	   public override string CallNumber()
	        {
	            return base.CallNumber() + " with GPS";
	        }
	 
	        public override string SendMessage()
	        {
	            return base.SendMessage() + " with GPS";
	        }
	    }
	 
	    public class DecoratorBlueTooth : Decorator
	    {
	        public DecoratorBlueTooth(AbstractCellPhone phone)
	            : base(phone)
	        { }
	 
	        public override string CallNumber()
	        {
	            return base.CallNumber() + " with BlueTooth";
	        }
	 
	        public override string SendMessage()
	        {
	            return base.SendMessage() + " with BlueTooth";
	        }
	 }


​	  
​	  给手机加上gps等装饰


  	

### 分类
创建型

行为型

结构型
    
    
