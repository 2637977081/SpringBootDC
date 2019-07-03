package com.cat.code.RbbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//队列名
@RabbitListener(queues = "hello")
public class ReceiveMessage {
    //接收到队列消息后的处理方法
    @RabbitHandler
    public void receiveMessage(String message){
        System.err.println("receive message："+message);
    }
}
