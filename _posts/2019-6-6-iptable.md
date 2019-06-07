---
layout: default

title: iptables

---

## iptables


### iptables
![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/iptables.png)

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/iptables2.png)

### docker
#### 容器对外请求数据

如果Docker0中的容器请求外部的数据，那么他的数据包将会发送到网关172.17.0.1处。当数据包到达网关后，将会查询主机的路由表，确定数据包将从那个网卡发出。iptables负责对数据包进行snat转换，将原地址转为对应网卡的地址，因此容器对外是不可见的。

#### 外部对容器请求数据

外部想要访问容器内的数据，首先需要将容器的端口映射到宿主机上。这时候docker会在iptables添加转发规则，把接收到的数据转发给容器。


![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/iptables3.png)

### reference
https://www.zsythink.net/archives/1199