---
layout: default

title: opencart架构分析

---

##opencart架构分析

###技术框架
OpenCart基于MVC(+L)架构，catalog/controller路径才是前台网站真正的控制层。Action对象会将route参数解析成准确的controller文件（即控制层类）、调用的函数以及所需的参数，然后Front对象调用相应PHP文件中的函数，并传入参数。以route=common/home为例，Action定位到catalog/controller/common/home.php文件，调用的函数为index()，无参数，然后Front根据这些信息进行跳转。

进入到controller文件之后，主要会进行如下的操作：

检查是否传入参数（POST或GET），若有则优先进行处理

从language目录下的对应文件中读出所需的常量字符串（使用保存在Controller对象中的Language对象，controller层的所有类都继承自Controller。）

调用model目录下的对应文件中的类访问数据库，获取所需的数据

所有需要输出的数据都保存到data数组中

通过template指向view目录下对应tpl模板文件，通过children数组指向需要一同输出的controller（一般是页面的头部header和尾部footer）

调用Controller对象的render函数对data和view层模板进行组装，然后通过Response对象的setOutput函数输出，返回结果

###订单业务逻辑

1.加入购物车

商品信息和用户信息放入数据库，主要有session_id、cusotomer_id、product_id字段

2.结账

checkout/checkout

2.1填写配送地址、支付方式

checkout/payment_address、checkout/shipping_address、checkout/payment_method

mcc_address保存账单地址和配送地址

2.2确认订单

在确认支付方式的时候生成订单，将用户信息，支付信息，配送信息存入mcc_order表，确认前的信息都被保存在session中。

controller：checkout/confirm/

mcc_product保存商品详情，mcc_order订单信息，mcc_order_product订单里的商品信息，个数、价格等
