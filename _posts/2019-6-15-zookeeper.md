---
layout: default

title: zookeeper

---

## zookeeper

创建唯一顺序id怎么做到的

高性能、高可用、写操作顺序性

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

### 数据结构

树形结构，和文件系统的目录结构相似

DataTree

### 角色

leader 读、写、选举

follower 读、选举

observer 读

### 事件监听器

允许用户在指定节点上注册一些watcher

### 消息广播

leader为follower服务器各自分配一个单独的队列，然后将需要广播的事务proposal依次放入队列中，并且根据fifo策略进行消息发送。

每个follower服务器在接收到这个事务之后，首先将其以事务日志的形式写入本地磁盘，成功写入之后发反馈给leader服务器一个ack响应。

当leader服务器收到超过半数follower的ack之后，广播一个commit消息给所有follower，同时自身也会完成事务提交。

follower收到commit之后，完成事务提价

### 崩溃恢复

### 选举策略

事务id最大

### server工作状态

LOOKING：当前Server不知道leader是谁，正在搜寻
LEADING：当前Server即为选举出来的leader
FOLLOWING：leader已经选举出来，当前Server与之同步

 ```
Follower主要有四个功能：
1. 向Leader发送请求（PING消息、REQUEST消息、ACK消息、REVALIDATE消息）；
2 .接收Leader消息并进行处理；
3 .接收Client的请求，如果为写请求，发送给Leader进行投票；
4 .返回Client结果。
Follower的消息循环处理如下几种来自Leader的消息：
1 .PING消息： 心跳消息；
2 .PROPOSAL消息：Leader发起的提案，要求Follower投票；
3 .COMMIT消息：服务器端最新一次提案的信息；
4 .UPTODATE消息：表明同步完成；
5 .REVALIDATE消息：根据Leader的REVALIDATE结果，关闭待revalidate的session还是允许其接受消息；
6 .SYNC消息：返回SYNC结果到客户端，这个消息最初由客户端发起，用来强制得到最新的更新。
 ```

