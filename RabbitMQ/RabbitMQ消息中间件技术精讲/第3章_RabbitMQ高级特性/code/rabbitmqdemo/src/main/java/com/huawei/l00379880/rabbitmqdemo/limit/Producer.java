/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-07-16 00:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.rabbitmqdemo.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
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

        // 4.指定我们的消息投递模式：消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_qos_exchange";
        String routingKey = "qos.save";

        // 5.发送一条消息
        String msg = "RabbitMQ QOS Message";

        for (int i = 0; i < 5; i++) {
            channel.basicPublish(exchangeName, routingKey, true, null, msg.getBytes());
        }
    }
}
