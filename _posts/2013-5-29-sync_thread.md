---
layout: default

title: windows线程同步

---
## windows线程同步  
### volatile限定符
阻止编译器对变量的代码优化，确保当读取变量时是从内存中读取而不是寄存器。防止出现其他一个线程修改了全局变量，而线程中操作变量的代码优化后是操作该变量第一次搬到的寄存器里的值。  

所以多线程编程时，应该对那些共享变量加上volatile类型。    

### 用户模式下的线程同步
#### 原子访问
保证对值的修改是以原子方式进行。    

InterlockedExchangeAdd等,涉及加减、与或。   

Interlocked函数调用一次占用50个以内的时钟周期。  

除了对整数或布尔值进行原子操作外，还能对Interlocked单向链表的栈进行原子操作。  

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/interlock_stack.PNG)

##### 旋转锁spinlock
利用原子操作函数实现旋转锁  

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/spinlock.PNG)

建议少用，因为长时间的while循环消耗cpu资源，应该采用在等待过程中，线程被挂起不浪费CPU资源，并由系统唤醒线程的机制。    

原子访问只能原子访问一个变量，不能控制访问一些共享资源，旋转锁轮询机制浪费CPU，因此引入：

#### 关键段critical section

	VOID EnterCriticalSection(PCRITICAL_SECTION pcs)  
EnterCriticalSection函数会检查pcs结构中的变量，这些变量表明是否有其他线程在访问资源。  

如果成员变量表示有其他线程在使用资源，则该函数会使用一个事件内核对象把调用线程切换到等待状态，不会浪费CPU时间。系统会记住该线程想要访问这个资源，当其他线程离开关键段，系统会更新该成员变量且唤醒该线程。
  
LeaveCriticalSection函数会检查是否关键段没有被任何线程访问，如果没有则唤醒那些等待着的线程。  

**enter和leave函数内部都是通过原子方式来检查测试成员变量。**   

缺点：   

能且只能用在一个进程中的多线程同步。可能陷入死锁，因为我们无法为进入关键段的线程设置最大等待时间  

#### 关键段+旋转锁
使用关键段的线程在得不到资源访问权的时候，会被系统挂起，从用户态切换到内核态，消耗的CPU周期比较大(1000个cpu周期)，切换过程中，占用资源的线程可能已经释放控制。所以可以加入旋转锁，在得不到资源访问权，线程切换前，自循环一段时间。  

#### slim读、写锁
同关键段原理，区别的地方在读资源线程可以允许其他读线程访问同一资源，而写线程不允许其他线程读写资源。  

写线程使用AcquireSRWLockExclusive,ReleaseSRWLockExclusive两个函数。  

读线程使用AcquireSRWLockShared，ReleaseSRWLockShared。 

#### 条件变量
在共享区域外面有关键段的条件下，进入关键段后，根据条件变量决定是否释放关键段的锁，以原子方式把锁释放并将自己阻塞，直到某一个条件成立为止。可以用于读写队列同步线程，例如对于读线程，进入关键段后，当队列为空时，就释放锁并阻塞自己，直到其他线程写完队列后唤醒该线程。  

主要函数SleepConditionVariableCS、SleepConditionVariableSRW、WakeConditionVariable、WakeAllConditionVariable  

### 用内核对象进行线程同步
较之用户模式下的线程同步该机制的缺点是调用内核模式下的线程同步函数，调用线程从用户态切换到内核态，切换耗时。  

几乎所有内核对象内部维护一个变量true、false表示该对象触发、或触发（进程、线程、作业、文件以及控制台的标准输入、输出、错误流、事件、可等待的计时器、信号量、互斥量）。
   
等待函数WaitForSingleObject、WaitForMultipleObjects等待内核对象触发。
  
可等待的计时器内核对象：指定某个时刻触发事件或每隔一段时间触发事件。  

信号量：信号量内核对象维护一个资源计数，当计数大于0，信号量对象处于触发状态。  

互斥量：与用户态下的关键段相似，不同于其他内核对象，互斥量对象维护了当前运行的线程ID。   

消费者生成者模型原理：

利用信号量和互斥量创建线程安全的队列。用互斥量锁住公共队列；生产者令信号量表示的资源数加1，使得信号量处于触发状态；消费者wait互斥量与信号量都处于触发状态，才能拿队列里的产品。

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/mutex_cs.PNG)




