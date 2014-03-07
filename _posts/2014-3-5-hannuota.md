---
layout: default
title: 汉诺塔

---
##汉诺塔

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/hannoi.JPG)

###问题

把n个盘子从A搬到C，并按从小到大排列。写出搬移步骤

###分解
1. 把n个盘子搬到C之前，需要把n-1个盘子搬到B柱。
2. 把A柱最底部盘子搬到C柱
3. 把B柱上的n-1盘子通过A搬到C

1. f(n, a, b, c) = f(n-1, a, c, b)
2. a->c return
3. f(n-1, b, a, c)