package com.cat.code.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lvgang
 * @Time: 2019/12/4 17:41
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${kafka.consumer.group-id}")
    private String consumerGroupId;
    @Value("${kafka.consumer.auto-offset-reset}")
    private String consumerAutoOffsetReset;
    @Value("${kafka.consumer.max-poll-records}")
    private String consumerMaxPollRecords;
    @Value("${kafka.producer.retries}")
    private String producerRetries;
    @Value("${kafka.producer.batch-size}")
    private String producerBatchSize;
    @Value("${kafka.producer.linger-ms}")
    private String producerLingerMs;
    @Value("${kafka.producer.buffer-memory}")
    private String producerBufferMemory;


    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
        factory.setBatchListener(true); // 开启批量监听
        return factory;
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> config = new HashMap<>();
        //kafka地址
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        //组id
        config.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerAutoOffsetReset);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerMaxPollRecords);
        config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return config;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.RETRIES_CONFIG, producerRetries); // 重试，0为不启用重试机制
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, producerBatchSize); // 控制批处理大小，单位为字节，默认为16384
        properties.put(ProducerConfig.LINGER_MS_CONFIG, producerLingerMs); // 批量发送，延迟为5毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量,默认为0
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, producerBufferMemory); // 生产者可以使用的总内存字节来缓冲等待发送到服务器的记录,默认为33554432，使用默认值即可

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
