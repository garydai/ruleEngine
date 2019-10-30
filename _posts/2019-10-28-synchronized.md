---
layout: default

title: synchronized



---

## synchronized



当执行 monitorenter 时，如果目标锁对象的计数器为 0，那么说明它没有被其他线程所持有。在这个情况下，Java 虚拟机会将该锁对象的持有线程设置为当前线程，并且将其计数器加 1。

对象头中的标记字段（mark word）。它的最后两位便被用来表示该对象的锁状态。其中，00 代表轻量级锁，01 代表无锁（或偏向锁），10 代表重量级锁，11 则跟垃圾回收算法的标记有关



![image-20191029102506877](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/image-20191029102506877.png)

当对象状态为偏向锁（biasable）时，`mark word`存储的是偏向的线程ID；当状态为轻量级锁（lightweight locked）时，`mark word`存储的是指向线程栈中`Lock Record`的指针；当状态为重量级锁（inflated）时，为指向堆中的monitor对象的指针。

### 重量级锁

加锁阻塞，解锁唤醒，涉及到系统调用，性能不好

![image-20191029112625089](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/image-20191029112625089.png)

当一个线程尝试获得锁时，如果该锁已经被占用，则会将该线程封装成一个ObjectWaiter对象插入到cxq的队列尾部，然后暂停当前线程。当持有锁的线程释放锁前，会将cxq中的所有元素移动到EntryList中去，并唤醒EntryList的队首线程。

如果一个线程在同步块中调用了`Object#wait`方法，会将该线程对应的ObjectWaiter从EntryList移除并加入到WaitSet中，然后释放锁。当wait的线程被notify之后，会将对应的ObjectWaiter从WaitSet移动到EntryList中。

### 轻量级锁

**多个线程在不同的时间段请求同一把锁，也就是说没有锁竞争**

当进行加锁操作时，Java 虚拟机会判断是否已经是重量级锁。如果不是，它会在当前线程的**当前栈桢中划出一块空间，作为该锁的锁记录，并且将锁对象的标记字段复制到该锁记录中**

![image-20191029094053730](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/image-20191029094053730.png)



##### 加锁过程

![image-20191029110219278](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/image-20191029110219278.png)



##### 解锁过程

1.遍历线程栈,找到所有obj字段等于当前锁对象的Lock Record。

2.如果Lock Record的Displaced Mark Word为null，代表这是一次重入，将obj设置为null后continue。

3.如果Lock Record的Displaced Mark Word不为null，则利用CAS指令将对象头的mark word恢复成为Displaced Mark Word。如果成功，则continue，否则膨胀为重量级锁。

### 偏向锁

**假设只有一个线程获取锁**

重量级、轻量级加锁解锁都有一个或多个cas操作

在JDK1.6中为了**提高一个对象在一段很长的时间内都只被一个线程用做锁对象场景下的性能**，引入了偏向锁，在第一次获得锁时，会有一个CAS操作，之后该线程再获取锁，只会执行几个简单的命令，而不是开销相对较大的CAS命令。

**在线程进行加锁时，如果该锁对象支持偏向锁，那么 Java 虚拟机会通过 CAS 操作，将当前线程的地址记录在锁对象的标记字段之中，并且将标记字段的最后三位设置为 101。**

每当有线程请求这把锁，Java 虚拟机只需判断锁对象标记字段中：最后三位是否为 101，是否包含当前线程的地址，以及 epoch 值是否和锁对象的类的 epoch 值相同。如果都满足，那么当前线程持有该偏向锁，可以直接返回。

当请求加锁的线程和锁对象标记字段保持的线程地址不匹配时（而且 epoch 值相等，如若不等，那么当前线程可以将该锁重偏向至自己），Java 虚拟机需要撤销该偏向锁。这个撤销过程非常麻烦，它要求持有偏向锁的线程到达安全点，再将偏向锁替换成轻量级锁。

##### 解锁

因此偏向锁的解锁很简单，仅仅将栈中的最近一条`lock record`的`obj`字段设置为null。需要注意的是，偏向锁的解锁步骤中**并不会修改对象头中的thread id。**

### synchronized加锁过程

先偏向锁，再膨胀成轻量最后到重量级锁

![image-20191028132658880](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/image-20191028132658880.png)



![synchronized](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/synchronized10.png)

#### safepoint

那么当Java线程运行到safepoint的时候，JVM如何让Java线程挂起呢？这是一个复杂的操作。很多文章里面说了JIT编译模式下，编译器会把很多safepoint检查的操作插入到编译器的指令中

```verilog
0x01b6d627: call   0x01b2b210         ; OopMap{[60]=Oop off=460}    
                                       ;*invokeinterface size    
                                       ; - Client1::main@113 (line 23)    
                                       ;   {virtual_call}    
 0x01b6d62c: nop                       ; OopMap{[60]=Oop off=461}    
                                       ;*if_icmplt    
                                       ; - Client1::main@118 (line 23)    
 0x01b6d62d: test   %eax,0x160100      ;   {poll}    
 0x01b6d633: mov    0x50(%esp),%esi    
 0x01b6d637: cmp    %eax,%esi   

```

test  %eax,0x160100 就是一个safepoint polling page操作。当JVM要停止所有的Java线程时会把一个特定内存页设置为不可读，那么当Java线程读到这个位置的时候就会被挂起


### refrence

https://blog.51cto.com/14440216/2426781