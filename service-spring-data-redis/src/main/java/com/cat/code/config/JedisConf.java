package com.cat.code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConf {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;	
    @Value("${spring.redis.password}")
    private String redisPasswd;
    @Value("${spring.redis.database}")
    private int redisDb;
    @Value("${spring.redis.timeout}")
    private int redisTimeout;    
    @Value("${spring.redis.pool.max-total}")
    private int redisMaxTotal; 
    @Value("${spring.redis.pool.max-wait}")
    private int redisMaxWait;    
    @Value("${spring.redis.pool.max-idle}")
    private int redisMaxIdle;
    @Value("${spring.redis.pool.min-idle}")
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
