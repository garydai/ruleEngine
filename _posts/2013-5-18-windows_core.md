---
layout: default
title: windows核心编程笔记

---

##读windows核心编程笔记

###内核对象
内核对象是有内核创建和维护的内存块，该内存块是一个数据结构，标识该内存对象的信息.例如，进程对象有一个进程ID、进程基本优先级和一个退出代码。  
用户调用函数，令系统创建一个内核对象，返回该对象的句柄，用于索引句柄表。  
每个进程有一个句柄表，保存各个内核对象的内存块地址、访问屏蔽和标志位。  
###进程
进程由两部分组成：一个是操作系统用来管理进程的**内核对象**，一个是保存代码与数据的**地址空间**，还有动态内存分配的空间.    
###线程
线程负责执行进程地址空间中的代码，也有两部分组成,**线程内核对象**与**栈**.每个线程拥有属于自己的cpu寄存器和栈。多线程共享同一进程的内存空间与内核对象句柄表.    
新线程可以访问进程内核对象的所有句柄\进程内所有内存\其他线程的栈  
线程内核对象结构堆栈指针寄存器(SP)指向由创建线程时传入的两个参数函数入口地址和函数参数.指令寄存器(IP)指向RtlUserTreadStart函数.  

	VOID RtlUserThreadStart(PTHREAD_START_ROUTINE pfnStartAddr, PVOID pvParam){	
		__try{
			ExitThread((pfnStartAddr)(pvParam));
		}
		__except(UnhandledExceptionFiler(GetExceptionInformation())){
			ExitProcess(GetExceptionCode());
		}
	}
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/windows_thread.PNG)
    