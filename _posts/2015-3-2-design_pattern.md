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


一个工厂类，一个抽象产品类，多个具体产品类；工厂根据传入的参数，创建相应的产品实例。

把产品抽象

3.抽象工厂模式

加入抽象工厂

工厂方法模式：创建抽象工厂类，具体工厂类，实例化具体工厂，创建产品

4.单例模式

5.建造者模式

工厂模式创建一个产品，建造者模式创建多个产品合为一起

6.原型模式

对象拷贝

7.适配器模式

将一个类通过适配器变成满足另一个接口的类

8.代理模式

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

	代理类和被代理类继承同一个接口

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
		
		
		
		public class ConcreteSubject extends Subject{
		    
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

16.迭代子模式

迭代器iterator

17.责任链模式

每个对象持有对下一个对象的引用

18.命令模式

19.备忘录模式

20.状态模式

21.访问者模式

数据和操作解耦

22.中介者模式

23.解释器模式

24.装饰器模式

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
   
   
	   public abstract class Decorator:AbstractCellPhone
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
	  
	  
		  
		  public class DecoratorGPS : Decorator
	    {
	        public DecoratorGPS(AbstractCellPhone phone)
	            : base(phone)
	        { }
	 
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
	 
	  
	  给手机加上gps等装饰
  
  	
    
    
