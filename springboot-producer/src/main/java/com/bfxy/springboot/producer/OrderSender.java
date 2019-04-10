package com.bfxy.springboot.producer;

import com.bfxy.springboot.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by linsong
 * 2018-10-13
 */
@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendOrder(Order order) throws Exception{

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());

        rabbitTemplate.convertAndSend("order-exchange2",//exchange
                "order.abcd",//routingkey
                order,
                correlationData); //消息唯一ID );

    }




}
