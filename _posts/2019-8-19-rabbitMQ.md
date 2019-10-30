---
layout: default

title: RabbitMQ

---

## RabbitMQ
### AMQP
消息队列协议，如同http协议

### 组件
Broker: 接收和分发消息的应用，我们在介绍消息中间件的时候所说的消息系统就是Message Broker。

Virtual host: 出于多租户和安全因素设计的，把AMQP的基本组件划分到一个虚拟的分组中，类似于网络中的namespace概念。当多个不同的用户使用同一个RabbitMQ server提供的服务时，可以划分出多个vhost，每个用户在自己的vhost创建exchange／queue等。

Connection: publisher／consumer和broker之间的TCP连接。断开连接的操作只会在client端进行，Broker不会断开连接，除非出现网络故障或broker服务出现问题。

Channel: 如果每一次访问RabbitMQ都建立一个Connection，在消息量大的时候建立TCP Connection的开销将是巨大的，效率也较低。Channel是在connection内部建立的逻辑连接，如果应用程序支持多线程，通常每个thread创建单独的channel进行通讯，AMQP method包含了channel id帮助客户端和message broker识别channel，所以channel之间是完全隔离的。Channel作为轻量级的Connection极大减少了操作系统建立TCP connection的开销。

Exchange: message到达broker的第一站，根据分发规则，匹配查询表中的routing key，分发消息到queue中去。常用的类型有：direct (point-to-point), topic (publish-subscribe) and fanout (multicast)。


Queue: 消息最终被送到这里等待consumer取走。一个message可以被同时拷贝到多个queue中。

Binding: exchange和queue之间的虚拟连接，binding中可以包含routing key。Binding信息被保存到exchange中的查询表中，用于message的分发依据。

Exchange的类型:

direct : 
 	 这种类型的交换机的路由规则是根据一个routingKey的标识，交换机通过一个routingKey与队列绑定 ，在生产者生产消息的时候 指定一个routingKey 当绑定的队列的routingKey 与生产者发送的一样 那么交换机会吧这个消息发送给对应的队列。

fanout:
 	 这种类型的交换机路由规则很简单，只要与他绑定了的队列， 他就会吧消息发送给对应队列（与routingKey没关系）

topic:(因为*在这个笔记软件里面是关键字，所以下面就用星替换掉了)
 	  这种类型的交换机路由规则也是和routingKey有关 只不过 topic他可以根据:星,#（ 星号代表过滤一单词，#代表过滤后面所有单词， 用.隔开）来识别routingKey 我打个比方 假设 我绑定的routingKey 有队列A和B A的routingKey是：星.user B的routingKey是: #.user
 	那么我生产一条消息routingKey 为： error.user 那么此时 2个队列都能接受到， 如果改为 topic.error.user 那么这时候 只有B能接受到了
headers:
 	  这个类型的交换机很少用到，他的路由规则 与routingKey无关 而是通过判断header参数来识别的， 基本上没有应用场景，因为上面的三种类型已经能应付了。

### ack
可以通过手动ack机制告诉服务端消费成功