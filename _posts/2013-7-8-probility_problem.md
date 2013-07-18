---
layout: default
title: 概率题

---

##概率题
###题目
已知随机函数old_rand(),以p的概率产生0，以1-p的概率产生1，要求设计一个新的随机函数newRand(),使其以等概率生成1和0。

###解答

	def new_rand():
		
		first = old_rand()
		second = old_rand()
		if first == 0 and second == 1
			return 0
		if first == 1 and second == 0
			return 1
		else return new_rand()


###分析
返回0的概率不是简单的从第一个if中得出p(1-p)，第三步也会有概率返回0

设t = p(1-p)

返回0的概率： t + (1-2t)\*t + (1-2t)^2\*t + (1-2t)^3\*t + ... + (1-2t)^n\*t + ... ~~~ t/(1-1+2t) = 1/2

同理得出返回1的概率：1/2


