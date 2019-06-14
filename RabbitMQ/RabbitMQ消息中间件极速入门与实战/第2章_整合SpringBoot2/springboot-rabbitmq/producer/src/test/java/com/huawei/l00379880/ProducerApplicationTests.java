package com.huawei.l00379880;

import cn.hutool.core.util.IdUtil;
import com.huawei.l00379880.entity.Order;
import com.huawei.l00379880.util.MqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTests {

    @Autowired
    private MqSender mqSender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSend1() throws Exception {
        Order order = new Order();
        order.setId(IdUtil.simpleUUID());
        order.setName("测试订单1");
        order.setMessageId(IdUtil.simpleUUID());
        mqSender.send(order);
    }
}
