---
layout: default
title: blade server 

---

## blade server

### 生命周期

接收客户的请求

执行中间件（如果有的话）

执行WebHook（如果有的话）

执行路由方法

向客户端发送响应

移除当前线程Request、Response