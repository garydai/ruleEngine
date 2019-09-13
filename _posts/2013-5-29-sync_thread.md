---
layout: default

title: windows线程同步

---
## windows线程同步  
### volatile限定符
volatile易变的、不可优化的，易变所以要直接从内存里拿变量

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


## linux线程同步
Pthread的同步机制有Mutex Lock、Spin Lock、Reader-Writter Lock、join、Condition Variables、semaphore、Barriers



互斥锁(mutex)

pthread_mutex_lock(&mutex);

条件变量

信号量



### 条件变量

##### 有了互斥变量pthread_mutext_t为什么还要引入条件变量pthread_cond_t呢





条件变量是线程的另外一种同步机制，这些同步对象为线程提供了会合的场所，理解起来就是两个（或者多个）线程需要碰头（或者说进行交互-一个线程给另外的一个或者多个线程发送消息），我们指定在条件变量这个地方发生，一个线程用于修改这个变量使其满足其它线程继续往下执行的条件，其它线程则接收条件已经发生改变的信号。

条件变量同锁一起使用使得线程可以以一种无竞争的方式等待任意条件的发生。所谓无竞争就是，条件改变这个信号会发送到所有等待这个信号的线程。而不是说一个线程接受到这个消息而其它线程就接收不到了。

条件变量一共也就pthread_cond_init、pthread_cond_destroy、pthread_cond_wait、pthread_cond_timedwait、pthread_cond_signal、pthread_cond_broadcast这么几个函数

```
推荐用法

class Condition4 : public ConditionBase
{
public:
    Condition4()
        : signal_(false)
    {
    }

    void wait()
    {
        pthread_mutex_lock(&mutex_);
        while (!signal_)
        {
            pthread_cond_wait(&cond_, &mutex_);
        }
        signal_ = false;
        pthread_mutex_unlock(&mutex_);
    }

    void wakeup()
    {
        pthread_mutex_lock(&mutex_);
        signal_ = true;
        pthread_cond_signal(&cond_);
        pthread_mutex_unlock(&mutex_);
    }

private:
    bool signal_;
};

POSIX规范为了简化实现，允许pthread_cond_signal在实现的时候可以唤醒不止一个线程

java的park，unpark就是采用这个线程同步方式

notify和notify_all在linux系统就调用pthread_cond_signal、pthread_cond_broadcast，wait调用pthread_cond_wait

void os::PlatformEvent::park() {       // AKA "down()"
  // Transitions for _event:
  //   -1 => -1 : illegal
  //    1 =>  0 : pass - return immediately
  //    0 => -1 : block; then set _event to 0 before returning

  // Invariant: Only the thread associated with the PlatformEvent
  // may call park().
  assert(_nParked == 0, "invariant");

  int v;

  // atomically decrement _event
  for (;;) {
    v = _event;
    if (Atomic::cmpxchg(v - 1, &_event, v) == v) break;
  }
  guarantee(v >= 0, "invariant");

  if (v == 0) { // Do this the hard way by blocking ...
    int status = pthread_mutex_lock(_mutex);
    assert_status(status == 0, status, "mutex_lock");
    guarantee(_nParked == 0, "invariant");
    ++_nParked;
    while (_event < 0) {
      // OS-level "spurious wakeups" are ignored
      status = pthread_cond_wait(_cond, _mutex);
      assert_status(status == 0, status, "cond_wait");
    }
    --_nParked;

    _event = 0;
    status = pthread_mutex_unlock(_mutex);
    assert_status(status == 0, status, "mutex_unlock");
    // Paranoia to ensure our locked and lock-free paths interact
    // correctly with each other.
    OrderAccess::fence();
  }
  guarantee(_event >= 0, "invariant");
}


void os::PlatformEvent::unpark() {
  // Transitions for _event:
  //    0 => 1 : just return
  //    1 => 1 : just return
  //   -1 => either 0 or 1; must signal target thread
  //         That is, we can safely transition _event from -1 to either
  //         0 or 1.
  // See also: "Semaphores in Plan 9" by Mullender & Cox
  //
  // Note: Forcing a transition from "-1" to "1" on an unpark() means
  // that it will take two back-to-back park() calls for the owning
  // thread to block. This has the benefit of forcing a spurious return
  // from the first park() call after an unpark() call which will help
  // shake out uses of park() and unpark() without checking state conditions
  // properly. This spurious return doesn't manifest itself in any user code
  // but only in the correctly written condition checking loops of ObjectMonitor,
  // Mutex/Monitor, Thread::muxAcquire and os::sleep

  if (Atomic::xchg(1, &_event) >= 0) return;

  int status = pthread_mutex_lock(_mutex);
  assert_status(status == 0, status, "mutex_lock");
  int anyWaiters = _nParked;
  assert(anyWaiters == 0 || anyWaiters == 1, "invariant");
  status = pthread_mutex_unlock(_mutex);
  assert_status(status == 0, status, "mutex_unlock");

  // Note that we signal() *after* dropping the lock for "immortal" Events.
  // This is safe and avoids a common class of futile wakeups.  In rare
  // circumstances this can cause a thread to return prematurely from
  // cond_{timed}wait() but the spurious wakeup is benign and the victim
  // will simply re-test the condition and re-park itself.
  // This provides particular benefit if the underlying platform does not
  // provide wait morphing.

  if (anyWaiters != 0) {
    status = pthread_cond_signal(_cond);
    assert_status(status == 0, status, "cond_signal");
  }
}

https://www.cnblogs.com/liyuan989/p/4240271.html

```

###java 线程
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/thread.png)
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/thread2.png)

线程的5个状态 

1、新建状态（New）：当线程对象对创建后，即进入了新建状态，如：Thread t = new MyThread();

2、就绪状态（Runnable）：当调用线程对象的start()方法（t.start();），线程即进入就绪状态。处于就绪状态的线程，只是说明此线程已经做好了准备，随时等待CPU调度执行，获取cpu 的使用权,并不是说执行了t.start()此线程立即就会执行

3、运行状态（Running）：可运行状态(runnable)的线程获得了cpu 时间片（timeslice） ，执行程序代码。 当CPU开始调度处于就绪状态的线程时，此时线程才得以真正执行，即进入到运行状态。注：就 绪状态是进入到运行状态的唯一入口，也就是说，线程要想进入运行状态执行，首先必须处于就绪状态中；

4、阻塞状态（Blocked）：处于运行状态中的线程由于某种原因，暂时放弃对CPU的使用权，，也即让出了cpu timeslice， 停止执行，此时进入阻塞状态，直到其进入到就绪状态，才 有机会再次被CPU调用以进入到运行状态。才有机会再次获得cpu timeslice 转到运行(running)状态 根据阻塞产生的原因不同，阻塞状态又可以分为三种：

 

4.1.等待阻塞：运行状态中的线程执行wait()方法，使本线程进入到等待阻塞状态；，JVM会4把该线程放入等待队列(waitting queue)中。

4.2.同步阻塞 –,运行(running)的线程在获取对象的同步锁时，若该同步锁被别的线程占用，获取synchronized同步锁失败 , 它会进入同步阻塞状态 ,则JVM会把该线程放入锁池(lock pool)中。

4.3.其他阻塞 – 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。运行(running)的线程执行Thread.sleep(long ms)或t.join()方法，或者发出了I/O请求时，JVM会把该线程置为阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入可运行(runnable)状态。

5、死亡状态（Dead）：线程执行完了或者因异常退出了run()方法，该线程结束生命周期。