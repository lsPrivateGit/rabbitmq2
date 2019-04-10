package com.bfxy.springboot;

import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.producer.OrderSender;
import com.bfxy.springboot.producer.RabbitOrderSender;
import com.bfxy.springboot.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootProducerApplicationTests {
    @Autowired
    private OrderSender orderSender;

    @Autowired
    private RabbitOrderSender rabbitOrderSender;


    @Autowired
    private OrderService orderService;

    @Test
    public void setOrderSender() throws Exception{
        Order order = new Order();
        order.setId("201808180000000001");
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());

        orderSender.sendOrder(order);
    }


    @Test
    public void setOrderSender2() throws Exception{
        Map<String,Object> properties = new HashMap<>();
        properties.put("number","12345");
        properties.put("sendtime",new SimpleDateFormat("yyyy/mm/dd"));

        Order order = new Order();
        order.setId("201808180000000001");
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());

        orderSender.sendOrder(order);
    }

    @Test
    public void testCreateOrder()throws Exception{
        Order order = new Order();
        order.setId("201808180000000002");
        order.setName("测试订单2");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderService.createOrder(order);

    }
}
