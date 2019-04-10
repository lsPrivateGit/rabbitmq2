package com.bfxy.springboot.consumer;

import com.bfxy.springboot.entity.Order;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by linsong
 * 2018-10-13
 */
@Component
public class orderReceiver {
/*
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "order-queue2",durable = "true"),
                    exchange = @Exchange(value = "order-exchange2",durable = "ture",type = "topic"),
                    key = "order.*"
            )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String,Object>headers,
                               Channel channel )throws  Exception{
        //消费者操作
        System.out.println("--------------收到消息，开始消费---------------");

        System.out.println("订单ID："+order.getId());

        Long deliveryTag= (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //ACK
        channel.basicAck(deliveryTag,false);

    }*/
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "order-queue3",durable = "true"),
                    exchange = @Exchange(value = "order-exchange3",durable = "ture",type = "topic"),
                    key = "order.*"
            )
    )
    @RabbitHandler
    public void onOrderMessage2(@Payload Order order, @Headers Map<String,Object>headers,
                               Channel channel )throws  Exception{
        //消费者操作
        System.out.println("--------------收到消息，开始消费---------------");

        System.out.println("订单ID："+order.getId());

        Long deliveryTag= (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //ACK
        channel.basicAck(deliveryTag,false);

    }

}
