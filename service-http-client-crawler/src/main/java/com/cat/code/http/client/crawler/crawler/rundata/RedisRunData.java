package com.cat.code.http.client.crawler.crawler.rundata;

import com.cat.code.http.client.crawler.config.redis.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: lvgang
 * @Time: 2019/11/12 14:50
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@Component
public class RedisRunData extends RunData {

    @Autowired
    private RedisTemplate redisTemplate;

    private String taskQueue;

    //设置队列key值
    public void setTaskQueue(String taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public boolean addUrl(String var1) {
        //添加url到队列中
        if(redisTemplate.sadd(taskQueue,var1)>0){
            return true;
        }
        return false;
    }

    @Override
    public String getUrl() {
        //获取队列url
        return redisTemplate.spop(taskQueue);
    }

    @Override
    public boolean getUrlExist() {
        //获取队列长度
        return redisTemplate.exist(taskQueue);
    }
}
