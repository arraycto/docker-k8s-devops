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

### 2.3.1 采用IO多路复用epoll模型

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
      
  

  
