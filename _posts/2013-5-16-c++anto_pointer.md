---
layout: default
title: C++ 智能指针

---

##what
是个对象,不是数据类型,对象里有个普通指针和内存被引用个数	
##why
普通指针缺点:  
普通指针指向的内存需要手动delete  
1.	内存被多次delete导致程序crash  
2.	内存没被delete导致内存泄露  
##how
