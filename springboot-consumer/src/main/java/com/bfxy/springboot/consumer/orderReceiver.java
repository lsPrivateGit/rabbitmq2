package com.bfxy.springboot.consumer;

import com.alibaba.fastjson.JSON;
import com.bfxy.springboot.entity.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by linsong
 * 2018-10-13
 */
@Component
@Slf4j
public class orderReceiver {
    private static int i = 1;
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "demo2-queue",durable = "true"),
                    exchange = @Exchange(value = "demo2",durable = "true",type = "topic"),
                    key = "demo2.#"
            )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order orderInfo, @Headers Map<String,Object>headers,
                               Channel channel ) throws IOException {

        log.info("开始接收消息");
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try{
            /**
             * 模拟异常

             if(i!=3){
             i++;
             throw new IOException("测试");
             }
             */
           /* if(orderInfo.getId()==1){
                i++;
                throw new IOException("测试");
            }*/
            log.info("{}收到消息为：{}",i, JSON.toJSONString(orderInfo));

            channel.basicAck(deliveryTag,false);
        }catch (IOException e){
            log.info("第{}次接收消息失败",i);
            /**
             * 重新放入队列 状态变为unacked
             * channel.basicNack(deliveryTag,false,true);
             * 抛弃此条消息
             * channel.basicNack(deliveryTag,false,false);
             */
            channel.basicNack(deliveryTag,false,true);
        }

    }
 /*   @RabbitListener(
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

    }*/

}
