---
layout: default
title: 数据挖掘笔记

---

##what 什么是数据挖掘
怎样在浩瀚的待选目标里找到自己需要的  
1. 别人推荐(朋友\专家)  
2. 事物本身(同一个牌子的衣服)

数据挖掘不仅仅用于推荐,还能用于选举\政治等等  

数据挖掘的核心是找到数据模型(patterns in data)

##推荐系统
1. collaborative filtering.挖掘与你品味相似的人他们使用过的东西
###how 怎样找到相似的人
1. distance measures  
1.1 Manhattan distance  
|x1 - x2|+ |y1 - y2|两个维度,可以扩展N个维度  
1.2 Euclidean distance  
两点距离sqrt((x1-x2)^2+(y1-y2)^2)
该距离方法,优点:计算快速;缺点:如果有些维度存在没有值,导致最终距离小,比较结果有失偏颇  
1.3 Minkowski distance  
整合以上函数(|xi - yi|^r)^1/r



