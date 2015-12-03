---
layout: default

title: python @符号

---

##python@符号

###思想

装饰器的概念，foo函数被timeit装饰


	#-*- coding: UTF-8 -*-
	import time
	 
	def foo():
	    print 'in foo()'
	 
	# 定义一个计时器，传入一个，并返回另一个附加了计时功能的方法
	def timeit(func):
	     
	    # 定义一个内嵌的包装函数，给传入的函数加上计时功能的包装
	    def wrapper():
	        start = time.clock()
	        func()
	        end =time.clock()
	        print 'used:', end - start
	     
	    # 将包装后的函数返回
	    return wrapper
	 
	foo = timeit(foo)
	foo()
	
	--------------
	
	import time
	 
	def timeit(func):
	    def wrapper():
	        start = time.clock()
	        func()
	        end =time.clock()
	        print 'used:', end - start
	    return wrapper
	 
	@timeit
	def foo():
	    print 'in foo()'
	 
	foo()
	
	加入@，少写了foo = timeit(foo)

###扩展

内置的装饰器有三个，分别是staticmethod、classmethod和property，作用分别是把类中定义的实例方法变成静态方法、类方法和类属性



####引用

[http://www.cnblogs.com/huxi/archive/2011/03/01/1967600.html]()





