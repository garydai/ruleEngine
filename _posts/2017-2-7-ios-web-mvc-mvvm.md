---
layout: default
title: 前端mvc mvvm

---

## ios web前端mvc mvvm，后端mvc

数据和UI通信

### ios 
#### mvc

ios中vc之间和mv之间的绑定

1.mc

controller -> model 

controller直接调用model数据

model -> controller

model通过kvo&notification机制与controller通信，观察者模式

	kvo
	kvo(键值观察)
	kvo是一个典型的观察者模式，观察者在键值改变时会得到通知。

	stockForKVO = [[StockData alloc] init];  
	[stockForKVO setValue:@"searph" forKey:@"stockName"];
	[stockForKVO setValue:@"10.0" forKey:@"price"];        
	[stockForKVO addObserver:self forKeyPath:@"price" options:NSKeyValueObservingOptionNew|NSKeyValueObservingOptionOld context:NULL];  
  
	-(void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context  
	{  
	    if([keyPath isEqualToString:@"price"])  
	    {  
	        myLabel.text = [stockForKVO valueForKey:@"price"];  
	    }  
	}  


	通知中心
	
	通知中心就是以NSNotificationCenter为中心，观察者往Center中注册对某个主题对象的变化感兴趣，主体对象通过NSNotificationCenter进行变化广播，所有的变化和监听行为都向同一个中心进行注册，所有对象的变化也通过同一个中心对外广播。

	NSNotificationCenter *notificationCenter = [NSNotificationCenter defaultCenter];
    //注册成为观察者 观察对象是Observer 
    [notificationCenter addObserver:self selector:@selector(doAction:) name:@"Observer" object:nil];
    
    NSNotificationCenter *notificationCenter = [NSNotificationCenter defaultCenter];
    
    [notificationCenter postNotificationName:@"Observer" object:self userInfo:info];
    
  
2.vc

controller -> view 

controller通过outlet，直接控制view

view -> controller

View通过action和delegate机制向Controller报告事件的发生


	代理机制，协议与代理
	A
	@protocol XDelegate <NSObject>
	- (void)xx;
	@end
	
	@interface X : NSObject
	@property (nonatomic, weak) id <XDelegate> delegate;
	@end

	 if ([self.delegate respondsToSelector:@selector(xx)]) {
        [self.delegate xx];
    }
    
    B
    @interface Y ()<XDelegate>
    
    - (void)xx{
    
	}

    
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/MVCPattern.png)

#### mvvm

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/MVVM.png)

viewmodel代替MVC中model的位置

####web前端 mvc模式

ios view与model之间是通过controller来通信，web前端view与model可以数据绑定，即将对象属性变化绑定到UI。

双向数据绑定指的是将对象属性变化绑定到UI，或者反之

双向绑定的实现有三个步骤：

1.我们需要一个方法来识别哪个UI元素被绑定了相应的属性 

2.我们需要监视属性和UI元素的变化 

3.我们需要将所有变化传播到绑定的对象和元素

可以用发布者-订阅者模式实现。
vm绑定
客户端根据用户的行为修改客户端Model -> 客户端更新和该Model相关的View -> 客户端Model发送sync请求到服务器，只包含改变了哪些数据 -> 服务器审核数据改动是否合法，只需回复是否修改成功 -> 客户端收到成功，什么都不用做；不成功，则把刚才改的View改回来，然后通知用户。



####vue.js

dom与数据绑定，数据改变，dom相应改变。


###yii2框架mvc模式
