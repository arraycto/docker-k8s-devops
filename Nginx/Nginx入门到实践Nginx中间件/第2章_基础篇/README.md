# 第2章 基础篇

## 2.1 什么是nginx

### 2.1.1 什么是中间件

![Nginx的中间件架构](https://img.mukewang.com/szimg/5d05c2c20001e51619201080.jpg)

### 2.1.2 Nginx概述

Nginx是一个开源且高性能、可靠的HTTP中间件、代理服务，支持海量并发请求的Web服务

## 2.2 常见的中间件服务

+ **httpd**:Apache基金会
+ **IIS**：微软
+ **gws**:google web server,谷歌,不对外开放
+ **nginx**: 俄罗斯F5，[介绍](https://baike.baidu.com/item/nginx/3817705?fr=aladdin),[官网](https://www.nginx.com/)

## 2.3 Nginx优势：多路IO复用

> 采用了IO多路复用epoll模型。下面阐述IO流处理的机制

+ epoll机制  
  ![epoll机制](https://img.mukewang.com/szimg/5d05e96d00010ce017281080.jpg)
+ IO复用
  + IO流处理的场景
    ![IO复用](https://img.mukewang.com/szimg/5d05ea100001886917281080.jpg)
  + IO流处理的难点：造成阻塞
    ![IO流处理的难点：造成阻塞](https://img.mukewang.com/szimg/5d05eb2e0001a38717281080.jpg)
  + IO流处理的两种方式(多线程和多进程)的劣势
    ![多进程多线程处理模式](https://img.mukewang.com/szimg/5d05eb980001f7c817281080.jpg)
    > 一个县城只能处理一个流的IO时间。如果想要同事处理多个流，要么多进程(fork)，要么多线程(pthread_create)，但是这两种方法的效率都不高。所以最好交给**一个线程去采用多路IO复用模式**，下一节讲
      
## 2.4 Nginx使用epoll模型的优势介绍

### 2.4.1 实现IO流非阻塞模式

```c
while true {
  for i in stream[] {
    if(i has data){
      read ultil unavaliable;
    }
  }
}
```

上面的代码中，如果所有的流都没有数据，那么只会白白浪费CPU。为了解决这么问题，Linux内核提供了下面的IO复用内核模式

### 2.4.2 IO复用内核模式种类

+ 类型一：select模型、poll模型

+ 类型二：epoll模型

发展历史是：`select--->poll--->epoll`

#### select模型

```c
while true {
  select(streams[]){ // 通过select来选取streams中有数据的来进行操作，避免流中没有数据时浪费CPU
    for i in stream[] {
      if(i has data){
        read ultil unavaliable;
      }
    }
  }
}
```

**`select`和`epoll`其实都相当于一个代理**
