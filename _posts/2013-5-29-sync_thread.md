---
layout: default
title: windows线程同步

---
##windows线程同步  
###用户模式下的线程同步
####原子访问
保证对值的修改是以原子方式进行。    
InterlockedExchangeAdd等,涉及加减、与或。   
Interlocked函数调用一次占用50个以内的时钟周期。  
除了对整数或布尔值进行原子操作外，还能对Interlocked单向链表的栈进行原子操作。  
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/interlock_stack.PNG)

#####旋转锁spinlock
利用原子操作函数实现旋转锁  
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/spinlock.PNG)
建议少用，因为长时间的while循环消耗cpu资源，应该采用在等待过程中，线程被挂起不浪费CPU资源，并由系统唤醒线程的机制。    

原子访问只能原子访问一个变量，不能控制访问一些共享资源，旋转锁轮询机制浪费CPU，因此引入：
####关键段critical section
	VOID EnterCriticalSection(PCRITICAL_SECTION pcs)  
EnterCriticalSection函数会检查pcs结构中的变量，这些变量表明是否有其他线程在访问资源。  
如果成员变量表示有其他线程在使用资源，则该函数会使用一个事件内核对象把调用线程切换到等待状态，不会浪费CPU时间。系统会记住该线程想要访问这个资源，当其他线程离开关键段，系统会更新该成员变量且唤醒该线程。  
LeaveCriticalSection函数会检查是否关键段没有被任何线程访问，如果没有则唤醒那些等待着的线程。  
enter和leave函数内部都是通过原子方式来检查测试成员变量。  



