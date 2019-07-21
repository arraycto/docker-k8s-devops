# 第3章 RabbitMQ高级特性

本章重点

+ 自定义消费者
+ 消息的ACK与重回队列
+ 消息的限流
+ TTL消息
+ 死信队列

## 3.1 消息如何保障100%的投递成功？

### 3.1.1 什么是生产端的可靠性投递

+ 保障消息的成功发出
+ 保障MQ节点的成功接收
+ 发送端收到MQ节点(Broker)确认应答
+ 完善的消息进行补偿机制

### 3.1.2 BAT/TMD互联网大厂的解决方案

+ 消息落库，对消息状态进行打标
  ![消息落库并对消息状态进行打标](images/消息落库并对消息状态进行打标.png)
+ 消息的延迟投递，做二次确认，回调检查，减少数据库操作
  ![二次确认和回调检查](images/二次确认和回调检查.png)

## 3.2 幂等性

> 消费端实现幂等性，就意味着，我们的消息永远不会被消费多次，即使我们收到了多条一样的消息

业界主流的幂等性操作如下

+ 唯一ID+指纹码机制，利用数据库主键去重
  ![唯一ID加指纹码](images/唯一ID加指纹码.png)
+ 利用Redis的原子性去实现幂等性
  ![利用Redis的原子性去实现幂等性](images/利用Redis的原子性去实现幂等性.png)

## 3.3 Confirm确认消息

### 3.3.1 Confirm消息确认机制的理解

+ 消息的确认，是指生产者投递消息后，如果Broker收到消息，则会给生产者一个应答
+ 生产者进行接收应答，用来确认这条消息是否正常地发送到Broker，这种方式也是消息的可靠性投递的核心保障

![Confirm消息确认机制](images/Confirm消息确认机制.png) 

### 3.3.2 Confirm消息机制的实现

代码流程图如下：

![Confirm消息机制的实现](images/Confirm消息机制的实现.png)

代码示例见 [Confirm消息机制的实现](code/rabbitmqdemo/src/main/java/com/huawei/l00379880/rabbitmqdemo/confirm)

## 3.4 Return消息机制

> 把不可达的消息返回给发送端

![Return消息机制](images/Return消息机制.png)

![Return消息机制的API配置](images/Return消息机制的API配置.png)

![Return消息机制图示](images/Return消息机制图示.png)

代码示例见 [Return消息机制代码范例](code/rabbitmqdemo/src/main/java/com/huawei/l00379880/rabbitmqdemo/returnlistener)

## 3.5 消费端的自定义监听

> 实现Consumer或者继承DefaultConsumer就可以代码原来麻烦的for循环；额

![消费端的自定义监听](images/消费端的自定义监听.png)

代码示例见 [此定义消费者Consumer代码范例](code/rabbitmqdemo/src/main/java/com/huawei/l00379880/rabbitmqdemo/consumer)

## 3.6 消息的限流

![消费端限流](images/消费端限流.png)
![消费端限流2](images/消费端限流2.png)

代码示例见 [消息限流的代码范例](code/rabbitmqdemo/src/main/java/com/huawei/l00379880/rabbitmqdemo/limit)

## 3.7 消费端ACK与重回队列

### 消费端的手工 ACK 和 NACK

![ACK与NACK](images/ACK与NACK.png)

+ 消费端进行消费的时候,如果由于业务异常我们可以进行日志的记录,然后进行补偿!
+ 如果由于服务器宕机等严重问题,那我们就需要手工进行 ACK 保障消费端消费成功!

[ACK代码示例](code/rabbitmqdemo/src/main/java/com/huawei/l00379880/rabbitmqdemo/ack)

### 消费端的重回队列

+ 对于没有处理成功的消息，把消息重新回递给Broker
+ 在实际应用中，都会关闭重回队列，也就是设置为false

[重回队列代码示例，和ACK的写在一起了](code/rabbitmqdemo/src/main/java/com/huawei/l00379880/rabbitmqdemo/ack)

## 3-8 TTL队列/消息

### TTL

+ TTL是Time To Live的缩写，也就是生存时间
+ RabbitMQ支持消息的过期时间，在消息发送时可以进行指定
+ RabbitMQ支持队列的过期时间，从消息入队列开始计算，只要超过了队列的超时时间设置，那么消息会自动地清除

