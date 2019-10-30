---
layout: default

title: 网络编程

---

# linux网络编程基础

## 最原始的web服务器

```
	// 监听端口
	open_listenfd()
	
	// 不断循环
	while(1) {
		
		// 新连接
		fd = accept()
		// 处理连接
		doit(fd)
	}
	
	
	// 处理连接
	doit() {
		// 读http header
		Rio_readline()
		
		// 解析uri
		parse_uri()
		
		// 如果是静态内容,则验证该文件是静态文件，然后返回文件内容
		serve_static()
		
		// 如果是动态内容，则验证该文件是可执行文件
		serve_dynamic()
	}
	
	// 处理动态内容
	serve_dynamic() {
		// 创建子进程
		Fork()		
		// 设置uri参数到环境变量
		setenv()
		// 重定向子进程标准输出到描述符fd
		Dup2(fd, STDOUT_FILENO)
		// 运行可执行文件
		Execve()	
		// 父进程等待子进程结束
		Wait()					
	}
	
```
## 引入并发编程改进web服务器
### 基于进程
### 基于IO多路复用
同时知道listenfd监听描述符和readfd读描述符是否准备好，并做相应处理
```
	Open_listenfd()
	
	init_pool()
	
	while(1) {
	
		select()
		
		if(listenfd) {
		
			Accept()
			// 新连接	
			add_client()
		}
		// 处理连接
		check_clients()
	}
	
```
### 基于线程
```
	Open_listenfd()
	
	while(1) {
		
		Accept()
		
		Pthread_create()
	}
```


