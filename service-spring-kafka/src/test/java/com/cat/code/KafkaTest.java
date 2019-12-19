package com.cat.code;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: lvgang
 * @Time: 2019/12/10 14:03
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceSpringKafkaApplication.class)
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void sendOne() throws Exception{
        String topic = "";
        kafkaTemplate.send(topic,"11");
    }
}
