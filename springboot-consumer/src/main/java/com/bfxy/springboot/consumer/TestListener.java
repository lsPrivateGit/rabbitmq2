package com.bfxy.springboot.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: LinSong
 * @Date: 2019/4/29 15:38
 */
@Component
@Slf4j

public class TestListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "demo2-queue",durable = "true"),
                    exchange = @Exchange(value = "demo2",durable = "true",type = "topic"),
                    key = "demo2.#"
            )
    )
    @RabbitHandler
    public void testListener(Message message, String msg, Channel channel) throws IOException {
        log.info("testListener收到消息：{}",msg);
        log.info("body:{}",message.getBody());

        if(true){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

        }else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
    }
}
