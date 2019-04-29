package com.bfxy.springboot.task;

import com.bfxy.springboot.constant.Constants;
import com.bfxy.springboot.entity.BrokerMessageLog;
import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.mapper.BrokerMessageLogMapper;
import com.bfxy.springboot.producer.RabbitOrderSender;
import com.bfxy.springboot.utils.FastJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by linsong
 * 2018-10-13
 */
@Component
public class RetryMessageTasker {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;


    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;


    //项目启动3秒钟每隔10秒抽取一次
   // @Scheduled(initialDelay = 3000,fixedDelay = 10000)
    @Transactional(rollbackFor = Exception.class)
    public void reSend(){
        System.err.println("------------------定时任务开始---------------");
        List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
        list.forEach(messageLog ->{
            if (messageLog.getTryCount() >= 3){
                //update fail message 超过3次修改为发送失败
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE,new Date());
            } else {
                //resend 修改发送次数
                brokerMessageLogMapper.update4ReSend(messageLog.getMessageId(),new Date());
                Order reSendOrder = (Order) FastJsonUtils.convertJSONToObject(messageLog.getMessage(),Order.class);
                try {
                    //重新发送
                    rabbitOrderSender.sendOrder(reSendOrder);

                }catch (Exception e){
                    e.printStackTrace();
                    System.err.println("-----------------------异常处理----------------");
                }


            }

        });


    }


}
