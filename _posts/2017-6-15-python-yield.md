---
layout: default
title: python-yield


---

## python-yield

### 迭代器(iterator)


python专门为for关键字做了迭代器的语法糖。在for循环中，Python将自动调用工厂函数iter()获得迭代器，自动调用next()获取元素，还完成了检查StopIteration异常的工作



### 生成器(constructor)

包含yield语句的函数会被特地编译成生成器

生成器函数在生成值后会自动挂起并暂停他们的执行和状态，他的本地变量将保存状态信息，这些信息在函数恢复时将再度有效

	def g(n):
		for i in range(n):
			yield i **2

	t = g(5) 生成器
	t.next() 0
	t.next() 1
	t.next() 4
	
	
	for i in g(5):
		print i,":",
		
	0 : 1 : 4 : 9 : 16 :

	
带有 yield 的函数不再是一个普通函数，而是一个生成器generator，可用于迭代

yield 是一个类似 return 的关键字，迭代一次遇到yield时就返回yield后面的值。重点是：下一次迭代时，从上一次迭代遇到的yield后面的代码开始执行。

