package com.bfxy.springboot.controller;

import com.bfxy.springboot.domain.ResponseBo;
import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.exception.LimitAccessException;
import com.bfxy.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: LinSong
 * @Date: 2019/4/11 17:00
 */
@RestController
public class RabbitmqController {


    @Autowired
    private OrderService orderService;

    @RequestMapping("/rabbitmq")
    public String getRabbitmq(@RequestParam ("id") String id) throws Exception {
        Order order = new Order();
        order.setId(id);
        order.setName("测试订单2");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderService.createOrder(order);
        return "hh";
    }

    @RequestMapping("/exception")
    public void testException(@RequestParam ("id") String id){
        throw new LimitAccessException("接口访问异常~~~");
    }


}
