package com.cat.code.RbbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 消息队列配置类,创建消息队列
 * 路由秘钥为hello
 */
@Configuration
public class RbbitConfig {

    @Bean
    public Queue messageQueue(){
        return new Queue("hello");
    }
}
