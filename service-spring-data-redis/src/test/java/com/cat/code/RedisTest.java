package com.cat.code;

import com.cat.code.config.RedisTemplate;
import com.cat.code.po.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @Author: lvgang
 * @Time: 2019/10/29 17:05
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceSpringDataRedisApplication.class)
public class RedisTest {
    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test1() throws Exception{
        String key = "user1";
        String json = objectMapper.writeValueAsString(new User(1L,"admin",21));
        redisTemplate.set(key,json);
        String value = redisTemplate.get(key);
        User user = objectMapper.readValue(value,User.class);
        logger.info("user:{}",user.toString());
    }

    @Test
    public void test2(){
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i->{
            executorService.execute(()->{
                logger.info("i:{}",i);
                redisTemplate.incr("count");//累加
                if(i%2==1){
                    redisTemplate.sadd("set",""+i);//添加
                }else {
                    String value = redisTemplate.spop("set");//取出
                    logger.info("第i次:{}获取{}",i,value);
                }
            });
        });
    }
}
