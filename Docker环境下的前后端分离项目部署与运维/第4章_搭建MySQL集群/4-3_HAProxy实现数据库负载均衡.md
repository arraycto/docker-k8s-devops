# 4-3_HAProxy实现数据库负载均衡

## HAProxy实现负载均衡的原理图

![HAProxy实现负载均衡的原理图](HAProxy实现负载均衡的原理图.jpg)

## 负载均衡中间件的对比

> 数据库多节点的同步用地协议是TCP/IP

+ 不用nginx的原因：刚刚支持TCP/IP协议，对于数据库同步支持不好
+ 不用Apache的原因:不支持TCP/IP协议
+ 不用LVS的原因：不支持在虚拟机里使用，不方便学习
+ 使用HAProxy的原因：对TCP/IP的支持好，有官方的Docker镜像，方便学习

![负载均衡中间件的对比](负载均衡中间件的对比.jpg)

## 使用HAProxy进行数据库的负载均衡

### 1.准备hasproxy的镜像

```shell
docker pull haproxy
```

### 2.自定义配置文件

+ `touch /usr1/l00379880/haproxy/haproxy.cfg`
