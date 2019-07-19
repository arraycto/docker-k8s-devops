/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-07-16 00:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.rabbitmqdemo.ack;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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

        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.#";
        String queueName = "test_ack_queue";

        // 4.声明交换机和队列并进行绑定设置，最后绑定路由key
        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        // 5.创建消费者,使用自定义的回调类，其中自己实现了handleDelivery()方法
        // 手工签收autoAck一定要设置为false
        channel.basicConsume(queueName, true, new MyConsumer(channel));
    }
}
