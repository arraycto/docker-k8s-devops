# IO多路复用之select、poll、epoll详解

> https://www.cnblogs.com/jeakeven/p/5435916.html

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

目前支持I/O多路复用的系统调用有 select，pselect，poll，epoll，I/O多路复用就是通过一种机制，一个进程可以监视多个描述符，一旦某个描述符就绪（一般是读就绪或者写就绪），能够通知程序进行相应的读写操作。但select，pselect，poll，epoll本质上都是同步I/O，因为他们都需要在读写事件就绪后自己负责进行读写，也就是说这个读写过程是阻塞的，而异步I/O则无需自己负责进行读写，异步I/O的实现会负责把数据从内核拷贝到用户空间。

与多进程和多线程技术相比，I/O多路复用技术的最大优势是系统开销小，系统不必创建进程/线程，也不必维护这些进程/线程，从而大大减小了系统的开销

## 一、使用场景

IO多路复用是指**内核一旦发现进程指定的一个或者多个IO条件准备读取，它就通知该进程**

IO多路复用适用如下场合：
+ 1）当客户处理多个描述符时（一般是交互式输入和网络套接口），必须使用I/O复用
+ 2）当一个客户同时处理多个套接口时，这种情况是可能的，但很少出现
+ 3）如果一个TCP服务器既要处理监听套接口，又要处理已连接套接口，一般也要用到I/O复用
+ 4）如果一个服务器即要处理TCP，又要处理UDP，一般要使用I/O复用
+ 5）如果一个服务器要处理多个服务或多个协议，一般要使用I/O复用

## 二、select、poll、epoll简介

epoll跟select都能提供多路I/O复用的解决方案。在现在的Linux内核里有都能够支持，其中**epoll是Linux所特有**，而**select则应该是POSIX所规定，一般操作系统均有实现**

### 1、select

+ 基本原理
  > select 函数监视的文件描述符分3类，分别是writefds、readfds、和exceptfds。调用后select函数会阻塞，直到有描述符就绪(有数据 可读、可写、或者有except)，或者超时(timeout指定等待时间，如果立即返回设为null即可)，函数返回。当select函数返回后，可以通过遍历fdset，来找到就绪的描述符
  
+ 基本流程
  ![select基本流程](https://images2015.cnblogs.com/blog/667911/201604/667911-20160426172125064-1263315531.png)
