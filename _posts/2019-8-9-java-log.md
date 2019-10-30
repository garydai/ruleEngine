---
layout: default

title: Java log

---

## Java log

#### log4j

#### jul 

java.util.log, java自带

默认打印info以上的log

#### jcl 

apache.common.log，遍历一个class string数组，class.forname，如果有class则生成该class实例，目前有log4j、jul两种类

#### slf4j
simple logging facade for java（门面）

通过绑定器绑定不同的log实现

提供一个桥接器，将其他日志框架桥接到slf4j

### spring log
spring4采用jcl，默认使用log4j框架，spring 5采用spring-jcl日志，改写了jcl，默认用jul



![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/javalog.jpg)