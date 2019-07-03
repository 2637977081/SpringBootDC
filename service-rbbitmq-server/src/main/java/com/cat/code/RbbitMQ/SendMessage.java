package com.cat.code.RbbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendMessage {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String message){
        message+= new Date();
        System.err.println("send message:"+message);
        //转换并发送消息  （队列名,消息体）
        amqpTemplate.convertAndSend("hello",message);
    }
}
