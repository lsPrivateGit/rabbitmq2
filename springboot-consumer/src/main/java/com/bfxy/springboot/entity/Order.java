package com.bfxy.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by linsong
 * 2018-10-13
 */
@Getter
@Setter
public class Order implements Serializable{

    private String id;

    private String name;

    private String messageId;//存储消息发送的唯一表示

    public Order() {

    }

    public Order(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }
}
