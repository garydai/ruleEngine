---
layout: default

title: iptables

---

## zookeeper

### 常用命令

ls /

create /test 1

set /test 2

get /test

delete

rmr

### 节点
1. 持久节点

creste 

2. 临时节点

create -e

3. 持久顺序节点

create -s

4. 临时顺序节点

create -s -e


### 存储
存放在内存

树形存储结构

### 集群
leader

follower

learner

ack过半

zab协议

一个节点默认最大数据量是1M
### 端口
同步端口

选举端口

### 持久化


![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/zookeeper.png)


![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/zookeeper2.png)


### 客户端
使用nio连接服务器

开启两个线程
sendThread

eventThread
