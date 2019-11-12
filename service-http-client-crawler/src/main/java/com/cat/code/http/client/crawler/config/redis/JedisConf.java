package com.cat.code.http.client.crawler.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConf {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port:6379}")
    private int redisPort;	
    @Value("${spring.redis.password}")
    private String redisPasswd;
    @Value("${spring.redis.database:0}")
    private int redisDb;
    @Value("${spring.redis.timeout:0}")
    private int redisTimeout;    
    @Value("${spring.redis.max-total:100}")
    private int redisMaxTotal; 
    @Value("${spring.redis.max-wait:-1}")
    private int redisMaxWait;    
    @Value("${spring.redis.max-idle:10}")
    private int redisMaxIdle;
    @Value("${spring.redis.min-idle:0}")
    private int redisMinIdle;
    
    @Bean
    public JedisPool redisPoolFactory()  throws Exception{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        jedisPoolConfig.setMaxIdle(redisMaxIdle);
        jedisPoolConfig.setMinIdle(redisMinIdle);
        jedisPoolConfig.setMaxWaitMillis(redisMaxWait);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
//        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        if (redisPasswd != null && redisPasswd.trim().length() == 0) {
        	redisPasswd = null;
        }
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort, redisTimeout, redisPasswd, redisDb);
        return jedisPool;
    }
 
}
