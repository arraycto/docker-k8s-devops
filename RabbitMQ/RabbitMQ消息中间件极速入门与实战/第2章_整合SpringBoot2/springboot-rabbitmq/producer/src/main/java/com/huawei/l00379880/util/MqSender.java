package com.huawei.l00379880.util;

import com.huawei.l00379880.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/***********************************************************
 * @note      : 消息发送工具类
 * @author    : 梁山广
 * @version   : V1.0 at 2019/6/14 10:36
 ***********************************************************/
@Component
public class MqSender {

    private final RabbitTemplate rabbitTemplate;

    public MqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送消息
     *
     * @param order 消息体
     * @throws Exception 遇到异常往外抛出
     */
    public void send(Order order) throws Exception {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getId());
        // order是消息体内容；correlationData是消息唯一id
        rabbitTemplate.convertAndSend("order-exchange", "order.abcd", order, correlationData);
    }
}
