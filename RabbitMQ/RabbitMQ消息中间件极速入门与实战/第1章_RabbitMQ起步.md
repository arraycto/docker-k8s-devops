# 第1章 RabbitMQ起步

> RabbitMQ是一个开源的消息代理和队列服务器，用来通过普通协议在完全不同的应用之间共享数据，RabbitMQ是使用Erlang语言来编写地，并且RabbitMQ是基于AMQP协议的

## RabbitMQ特点

+ 底层采用Erlang语言编写，拥有和原生Socket相近的性能和低延迟
+ 开源、性能优秀，稳定性好
+ 与SpringAMQP完美整合，API丰富
+ 集群模式丰富，表达式配置，HA模式，镜像队列模型
+ 可以保证数据不丢失的前提下做到高可靠和高可用
+ 一般会加HAproxy做负载均衡，加Keepalived实现高可用
+ AMQP全称：Advanced Message Queuing Protocol,高级消息队列协议

## AMQP协议模型

> 详解见博客 [深入理解AMQP协议](https://blog.csdn.net/weixin_37641832/article/details/83270778)

![AMQP协议模型](https://img3.mukewang.com/5d0243af0001310519201080.jpg)

## 安装RabbitMQ(使用Docker安装)

`docker run -d --hostname my-rabbit --name some-rabbit -p 8080:15672 -e RABBITMQ_DEFAULT_USER=root -e RABBITMQ_DEFAULT_PASS=aA111111 rabbitmq:3-management`

+ `-e RABBITMQ_DEFAULT_USER=root`:指定用户名
+ `-e RABBITMQ_DEFAULT_PASS=aA111111`:指定密码
