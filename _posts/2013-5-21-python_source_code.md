---
layout: default
title: python对象机制

---
##python对象机制
###对象机制
在python的世界里，一切都是对象  
1.对象头结构  

	#define PyObject_HEAD           \
	
	    _PyObject_HEAD_EXTRA        \
	
	    int ob_refcnt;          \ //引用计数
	
	    struct _typeobject *ob_type;//指向类型对象的指针

类型也是一个对象  
2.类型对象   

	typedef struct _typeobject {
	
	    PyObject_VAR_HEAD 
	
	    char *tp_name; /* 类型名For printing, in format "<module>.<name>" */
	
	    int tp_basicsize, tp_itemsize; /* 该类型的对象分配空间大小For allocation */
	
	 
	
	    /* Methods to implement standard operations */
	
	    destructor tp_dealloc;//析构函数指针
	
	    printfunc tp_print;//打印函数指针
	
	     ……
	
	    /* More standard operations (here for binary compatibility) */
	
	    hashfunc tp_hash;
	
	    ternaryfunc tp_call;
	
	    ……
	
	} PyTypeObject;

3.继承与多态
因为各种对象的开始内存都是PyObject_HEAD,可以把这些对象视为继承PyObject的子类  

	typedef struct _object { 
	
	    PyObject_HEAD 
	
	} PyObject;  

当python创建一个对象，比如PyIntObject对象时，分配内存，初始化，然后这个对象由PyObject*变量来维护，通过PyObject的对象类型指针来实现多态，例如打印函数  
  

	void Print(PyObject* object) 
	
	{ 
	
	    object->ob_type->tp_print(object); 
	
	} 

4.引用计数，垃圾回收机制 

	#define _Py_NewReference(op) ((op)->ob_refcnt = 1) //引用计数初始化
	
	#define _Py_Dealloc(op) ((*(op)->ob_type->tp_dealloc)((PyObject *)(op))) 
	
	#define Py_INCREF(op) ((op)->ob_refcnt++) 
	
	#define Py_DECREF(op)                   \ 
	
	    if (--(op)->ob_refcnt != 0)         \ 
	
	        ;            \ 
	
	    else                        \ 
	
	        _Py_Dealloc((PyObject *)(op)) 


当引用计数减为0，调用析构函数  

5.Python对象的分类  
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/python_object.PNG)

###整数对象
Python维护一个整数对象池，当删除整数对象时，不会回收内存，而把该内存归还给对象池。  
对于小整数([-5,100])，在解释器初始化的时候已经在对象池中创建了全部小整数对象。  
####数据结构
	#define BLOCK_SIZE  1000    /* 1K less typical malloc overhead */
	#define BHEAD_SIZE  8   /* Enough for a 64-bit pointer */
	#define N_INTOBJECTS    ((BLOCK_SIZE - BHEAD_SIZE) / sizeof(PyIntObject))

	struct _intblock {
	    struct _intblock *next;
	    PyIntObject objects[N_INTOBJECTS];
	};

sizeof(PyIntObject) 4字节引用计数，4字节类型对象指针，4字节整数值.  
所以一个block里有82个整数对象，block_list链表链接不同block，还有空闲链表链接空闲的整数对象。






