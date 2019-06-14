# 课程导航

+ RabbitMQ简介及AMQP协议
+ RabbitMQ安装和使用
+ RabbitMQ核心概念
+ 与SpringBoot2.x整合
+ 保证100%的消息可靠性投递方案落地
+ [老师代码](https://github.com/suxiongwei/springboot-rabbitmq)
+ [老师文章](https://www.imooc.com/article/49814)
+ idea生成serialVersionUID的步骤
  > File->setting->Editor-->Inspections->Serializationissues，将其展开后将Serialization issues-->serialzable class without "serialVersionUID"打上勾；然后实体类上有告警，自动修复生成serialVersionUID即可
+ 路由规则的模糊匹配
  + `order.*`:只可以匹配一个.和一个单词
  + `order.#`:可以匹配多个.和多个单词
+ 消息的实体类需要注意
  > 生产者和消费者的消息实体类必须在完全一样的包路径下，最好单独提取出来成为一个叫model的maven子模块

