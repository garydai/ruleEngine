---
layout: default

title: spring boot

---

## spring boot

### spring
DI（依赖注入IOC）

AOP（面向切面编程）

把有一些类注解，实例一次，放入ioc容器

通过autowired等注解，从ioc容器里拿实例

### spring mvc
使用spring实现mvc设计模式

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/springmvc.png)

url进入HandlerMapping，找到具体的controller实现类

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/springmvc2.png)

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/springmvc3.png)

核心组件

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/springmvc4.png)

### spring boot

创建一个基于spring的应用简单

自动配置

零xml配置

application.properties是spring boot默认的配置文件，spring boot默认会在以下两个路径搜索并加载这个文件

src\main\resources

src\main\resources\config


### 自动配置
springboot autoconfiguration

### 程序开始
SpringApplication.run（），进行自动配置

```
加载所有jar包里的META-INF的spring.factories文件
setInitializers((Collection) getSpringFactoriesInstances(
				ApplicationContextInitializer.class));
setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));



加载所有jar包里META-INF底下spring.factories配置，SpringFactoriesLoader
Set<String> names = new LinkedHashSet<>(SpringFactoriesLoader.loadFactoryNames(type, classLoader));



springboot autoconfiguration的meta-inf的spring.factories文件
# Initializers
org.springframework.context.ApplicationContextInitializer=\
org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer,\
org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener

# Application Listeners
org.springframework.context.ApplicationListener=\
org.springframework.boot.autoconfigure.BackgroundPreinitializer


在names处打个断点
names = {LinkedHashSet@979}  size = 6
 0 = "org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer"
 1 = "org.springframework.boot.context.ContextIdApplicationContextInitializer"
 2 = "org.springframework.boot.context.config.DelegatingApplicationContextInitializer"
 3 = "org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer"
 4 = "org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer"
 5 = "org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener"

```


### spring-boot-starter
#### 自动配置

在启动是springboot服务时，loadSpringFactories加载所有jar包里的META-INF/spring.factories文件，将该配置文件中的配置载入到Spring容器中




