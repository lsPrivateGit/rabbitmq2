package com.bfxy.springboot.service;

import com.bfxy.springboot.constant.Constants;
import com.bfxy.springboot.entity.BrokerMessageLog;
import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.mapper.BrokerMessageLogMapper;
import com.bfxy.springboot.mapper.OrderMapper;
import com.bfxy.springboot.producer.RabbitOrderSender;
import com.bfxy.springboot.utils.FastJsonUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by linsong
 * 2018-10-13
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RabbitOrderSender rabbitOrderSender;




    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Order order) throws Exception {
        //order current time
        Date orderTime = new Date();
        //order insert //业务入库
        orderMapper.insert(order);
        //log insert  构建消息日子记录对象
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        brokerMessageLog.setMessage(FastJsonUtils.convertObjectToJSON(order));
        brokerMessageLog.setTryCount(0);
        brokerMessageLog.setStatus("0");//设置订单的发送状态为0 表示发送中
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());
        brokerMessageLogMapper.insert(brokerMessageLog);
        rabbitOrderSender.sendOrder(order);
    }
}
