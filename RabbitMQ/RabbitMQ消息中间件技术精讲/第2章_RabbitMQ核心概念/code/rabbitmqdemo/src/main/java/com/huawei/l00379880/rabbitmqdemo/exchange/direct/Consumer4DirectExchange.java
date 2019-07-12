/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-07-12 07:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.rabbitmqdemo.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer4DirectExchange {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1.ConnectionFactory:获取连接工厂,并进行基础配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("122.112.151.149");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("aA111111");


        // 2.Connection:通过连接工厂创建一个连接
        // 支持自动重连
        connectionFactory.setAutomaticRecoveryEnabled(true);
        // 没连接成功地话，3s自动重连一次
        connectionFactory.setNetworkRecoveryInterval(3000);
        // 创建连接
        Connection connection = connectionFactory.newConnection();

        // 3.Channel:通过Connection创建Channel,连接后创建需要的数据通信信道，可发送和接收消息
        Channel channel = connection.createChannel();

        // 4.声明
        String exchangeName = "test_direct_exchange";
        String routingKey = "test.direct";
        String exchangeType = "direct";
        // Todo:Direct模式下exchangeName和queueName必须相等
        String queueName = "test_direct_exchange";
        // 声明自己的交换机Exchange
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        // 声明消息队列
        channel.queueDeclare(queueName, false, false, false, null);
        // 指定队列名和交换机名(Direct模式下两者相等)
        channel.queueBind(queueName, exchangeName, routingKey);

        // 5.接收消息
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 参数含义依次是：队列名称，是否自动ACK(签收)，消费者对象
        channel.basicConsume(queueName, true, consumer);
        // 循环获取消息
        while (true) {
            // 获取消息，如果没有消息，这一步将会一直阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息" + msg);
        }
    }
}
