/***********************************************************
 * @Description : 生产者
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-07-09 08:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.rabbitmqdemo.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.ConnectionFactory:获取连接工厂,并进行基础配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("122.112.151.149");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("aA111111");


        // 2.Connection:通过连接工厂创建一个连接
        Connection connection = connectionFactory.newConnection();

        // 3.Channel:通过Connection创建Channel,连接后创建需要的数据通信信道，可发送和接收消息
        Channel channel = connection.createChannel();

        // 设置消息的各种属性
        Map<String, Object> headers = new HashMap<>();
        headers.put("my1", "111");
        headers.put("my2", "222");
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2) // 2表示RabbitMQ重启消息仍然存在，1表示重启后未处理的消息丢掉
                .contentEncoding("UTF-8")
                .expiration("10000") // 10s过期
                .headers(headers)
                .build();

        // 4.Queue:通过Channel发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello rabbitmq";
            // 不指定Exchange,会默认走AMQP default的exchange，这时routingKey和consumer的QueueName是对应的，相等才能发消息
            // properties代表自己对消息属性的定制
            channel.basicPublish("", "test001", properties, msg.getBytes());
        }

        // 5.关闭相关连接
        channel.close();
        connection.close();
    }
}
