/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-07-12 07:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.rabbitmqdemo.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer4FanoutExchange {
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

        // 4.声明
        String exchangeName = "test_fanout_exchange";
        // 不需要routingKey

        // 5.发送
        for (int i = 0; i < 10; i++) {
            String msg = "Hello World RabbitMQ 4 Fanout Exchange";
            // Fanout类型的Exchange不会和RoutingKey产生任何绑定关系,随便设置
            channel.basicPublish(exchangeName, "sxsds", null, msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
