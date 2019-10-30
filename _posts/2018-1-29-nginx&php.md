---
layout: default

title: nginx&php

---

## nginx&php

nginx转发请求给php-fpm，php-fpm是fastcgi协议的一种实现。衔接webserver和业务逻辑


### 交互流程
php-fpm初始化，监听用户请求


nginx webserver收到用户请求，根据nginx配置，把请求转发到php-fpm，php-fpm把请求输入到php框架比如yii2，框架根据不同请求，进行逻辑处理。









