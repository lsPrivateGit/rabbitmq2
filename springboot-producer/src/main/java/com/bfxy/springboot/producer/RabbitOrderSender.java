package com.bfxy.springboot.producer;

import com.bfxy.springboot.constant.Constants;
import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.mapper.BrokerMessageLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by linsong
 * 2018-10-13
 */
@Slf4j
@Component
public class RabbitOrderSender {

    //自动注入RabbitTemplate模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;


    //回调函数：confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback(){

        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("correlationData:"+correlationData);
            String messageId = correlationData.getId();
            if (ack){
                //如果confim返回成功 则进行更新
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId,Constants.ORDER_SEND_SUCCESS,new Date());
                System.out.println("消息发送成功！");
            } else {
                //失败则进行具体的后续操作：重试或者补偿等手段
                System.err.println("异常处理。。。");
            }
        }
    };

    //发送消息方法调用：构建自定义对象消息
    public void sendOrder(Order order) throws Exception{
        rabbitTemplate.setConfirmCallback(confirmCallback);
       /* rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
                    @Override
                    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                        log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
                    }
                });*/
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("demo2","demo2.aaa",order,correlationData);
    }




}
