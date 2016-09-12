---
layout: default

title: ios property

---

##ios-@property

1.

	@interface Car : NSObject
	
	@property BOOL running;
	
	@end
	
	编译器翻译成
	
	
	- (BOOL)running {
		return _reunning;
	}
	
	- (void)setRunning:(BOOL)newValue {
		_running = newValue;
	}
	 setter和getter方法
	 
1.1

	getter= 跟 setter=	 
	改变默认getter、setter方法名
	@property (getter=isRunning) BOOL running;
	
	Car *honda = [[Car alloc] init];
	honda.running = YES; // [honda setRunning: YES]
	NSLog(@"%d", honda.running); // [honda isRunning]
	NSLog(@"%d", [honda running]); // Error: method no longer exists
	
1.2 

	readonly 只读
	
1.3

	nonatomic
	
	property默认是线程安全，属性是原子性的
	
1.4

	strong
	
	强引用，循环引用会无法释放对象
	
1.5

	weak
	
	弱引用，父对象销毁，子对象指向父对象的指针置为0
	
1.6
 
 	copy
 	
 	创建被分配的值的拷贝，而不是直接引用原对象




[http://www.pandara.xyz/2015/10/09/objc_property/]()












