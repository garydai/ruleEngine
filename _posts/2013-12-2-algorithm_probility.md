---
layout: default
title: 算法题-概率


---

##概率题

###题目

已知等概率产生A、B、C、D，要求等概率产生ABCD的全排列。

###分析
已知rand03，等概率产生0到3的数，代表A到B。

借鉴洗牌算法，首先从4个字母里rand03产生一个字母，接着从剩下的3个字母里rand02产生一个字母，以此类推，产生最后的排列。

则ABCD该排列的概率等于1/4\*1/3\*1/2=1/24。

那么，如何通过rand03，产生rand02呢。

	while(1)
		r = ran03
		if r == 3:
			continue
		else
			return r

