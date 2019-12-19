package com.cat.code.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: lvgang
 * @Time: 2019/12/10 13:41
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@Component
public class ConsumerListener {
    private final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    @KafkaListener(topics = "kafka_one")
    public void onMessageOne(String message){
        logger.info("接受数据：{}", message);
    }


    @KafkaListener(topics = "kafka_list",containerFactory = "batchFactory")
    public void onMessageList(List<ConsumerRecord<?, ?>> records){
        for (ConsumerRecord<?, ?> record : records) {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            logger.info("Received: " + record);
            if (kafkaMessage.isPresent()) {
                Object message = record.value();
                logger.info("接受数据：{}", message);
            }
        }
    }
}
