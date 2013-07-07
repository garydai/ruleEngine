---
layout: default
title: 编程实现k/n的概率

---
##编程实现k/n的概率
###问题

	m = random(1, n);   
	if(m \< k)
		return m;
	else -1;

k/n的概率返回k内的数字，k内的m数字被选中的概率是多少?

是k/n呢还是k/n * 1/k？

	count = 0
	count2 = 0
	summ = 10000000
	for i in range(summ):
		k = 6
		m = random.choice(range(10))
		if(m < k):
			count = count + 1
			if m == 1:
				count2 = count2 + 1
	
	print count * 1.0 / summ 0.5998247 ~ 6/10
	print count2 * 1.0 / count 0.166646688608 ~ 6/10 * 1/6

###结论
k内的m数字被选中的概率是k/n * 1/k	
	