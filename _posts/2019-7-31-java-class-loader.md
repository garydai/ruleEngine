---
layout: default

title: Java ClassLoader

---

## Java ClassLoader

ClassLoader的具体作用就是将class文件加载到jvm虚拟机中去，程序就可以正确运行了。但是，jvm启动的时候，并不会一次性加载所有的class文件，而是根据需要去动态加载。

classloader在自己专属路径下查找class文件，并加载生成内存形式的Class对象

### BootstrapClassLoader
BootstrapClassLoader 负责加载 JVM 运行时核心类，这些类位于 $JAVA_HOME/lib/rt.jar 文件中，我们常用内置库 java.xxx.* 都在里面，比如 java.util.、java.io.、java.nio.、java.lang. 等等。这个 ClassLoader 比较特殊，它是由 C 代码实现的，我们将它称之为「根加载器」。

### ExtensionClassLoader
ExtensionClassLoader 负责加载 JVM 扩展类，比如 swing 系列、内置的 js 引擎、xml 解析器 等等，这些库名通常以 javax 开头，它们的 jar 包位于 $JAVA_HOME/lib/ext/*.jar 中，有很多 jar 包。

static class ExtClassLoader extends URLClassLoader

### AppClassLoader
AppClassLoader 才是直接面向我们用户的加载器，它会加载 Classpath 环境变量里定义的路径中的 jar 包和目录。我们自己编写的代码以及使用的第三方 jar 包通常都是由它来加载的。

static class AppClassLoader extends URLClassLoader

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/classloader.png)

![](https://github.com/garydai/garydai.github.com/raw/master/_posts/pic/classloader2.png)

AppClassLoader 在加载一个未知的类名时，它并不是立即去搜寻 Classpath，它会首先将这个类名称交给 ExtensionClassLoader 来加载，如果 ExtensionClassLoader 可以加载，那么 AppClassLoader 就不用麻烦了。否则它就会搜索 Classpath 


而 ExtensionClassLoader 在加载一个未知的类名时，它也并不是立即搜寻 ext 路径，它会首先将类名称交给 BootstrapClassLoader 来加载，如果 BootstrapClassLoader 可以加载，那么 ExtensionClassLoader 也就不用麻烦了。否则它就会搜索 ext 路径下的 jar 包


一个AppClassLoader查找资源时，先看看缓存是否有，缓存有从缓存中获取，否则委托给父加载器

ClassLoader类

getParent()实际上返回的就是一个ClassLoader对象parent，parent的赋值是在ClassLoader对象的构造方法中，它有两个情况：

由外部类创建ClassLoader时直接指定一个ClassLoader为parent。
由getSystemClassLoader()方法生成，也就是在sun.misc.Laucher

通过getClassLoader()获取，也就是AppClassLoader。一个ClassLoader创建时如果没有指定parent，那么它的parent默认就是AppClassLoader，指定一个ClassLoader的父类加载器为启动类加载器，只需要将其parent指定为null即可。


#### loadClass
执行findLoadedClass(String)去检测这个class是不是已经加载过了。

执行父加载器的loadClass方法。如果父加载器为null，则jvm内置的加载器去替代，也就是Bootstrap ClassLoader。这也解释了ExtClassLoader的parent为null,但仍然说Bootstrap ClassLoader是它的父加载器。自定义的ClassLoader，默认的parent父加载器是AppClassLoader，因为这样就能够保证它能访问系统内置加载器加载成功的class文件

如果向上委托父加载器没有加载成功，则通过findClass(String)查找。

### Context ClassLoader 线程上下文类加载器
每个Thread都有一个相关联的ClassLoader，默认是AppClassLoader。并且子线程默认使用父线程的ClassLoader除非子线程特别设置


Thread.currentThread().setContextClassLoader(diskLoader)设置线程的classLoader

### reference
https://juejin.im/post/5c04892351882516e70dcc9b

https://blog.csdn.net/briblue/article/details/54973413