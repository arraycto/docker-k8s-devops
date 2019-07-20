/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-07-18 22:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.rabbitmqdemo.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {

    private Channel channel;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    /**
     * 原来的while(true)替换成这个了
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("----------------consume message-------------");
        System.out.println("body:" + new String(body));
        if (0 == (Integer) properties.getHeaders().get("num")) {
            // 等于0时把消息重新返回队列,会导致5条中只有4条被消费
            channel.basicNack(envelope.getDeliveryTag(), false, true);
        } else {
            // 自定义签收规则，返回Ack后可以签收所有消息，否则只能签收basicQos里面prefetchCount个消息
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
