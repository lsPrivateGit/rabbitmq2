package com.bfxy.springboot.producer;

import com.bfxy.springboot.constant.Constants;
import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.mapper.BrokerMessageLogMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;
import java.util.Date;

/**
 * Created by linsong
 * 2018-10-13
 */
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
            } else {
                //失败则进行具体的后续操作：重试或者补偿等手段
                System.err.println("异常处理。。。");
            }
        }
    };

    //发送消息方法调用：构建自定义对象消息
    public void sendOrder(Order order) throws Exception{
       // rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange3","order.ABC",order,correlationData);
    }




}
