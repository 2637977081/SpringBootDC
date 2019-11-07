package com.cat.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: lvgang
 * @Time: 2019/10/29 16:27
 * @Email: lvgang@golaxy.cn
 * @Description: 配置redis连接池
 */
@Component
public class RedisTemplate {

    @Autowired
    private JedisPool jedisPool;

    /**
     * key-value存储
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String res = jedis.set(key, value);
            return (res == null || !res.equalsIgnoreCase("OK") ? false : true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * key-value存储设置过期时间
     * @param key
     * @param value
     * @param expire_time
     * @return
     */
    public boolean set(String key, String value, int expire_time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String res = jedis.set(key, value, "NX", "EX", expire_time);
            return (res == null || !res.equalsIgnoreCase("OK") ? false : true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * key-value存储设置过期时间，当存在替换为新的值
     * @param key
     * @param value
     * @param expire_time
     * @return
     */
    public boolean setex(String key, String value, int expire_time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String res = jedis.setex(key, expire_time, value);
            return (res == null || !res.equalsIgnoreCase("OK") ? false : true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 获取key-value 值
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String res = jedis.get(key);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * key是否存在
     * @param key
     * @return
     */
    public boolean exist(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Boolean res = jedis.exists(key);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * list左进队列
     * @param key
     * @param values
     * @return
     */
    public Long lpush(String key, String... values) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long ret = jedis.lpush(key, values);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * list 右进队列
     * @param key
     * @return
     */
    public String rpop(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.rpop(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * set key-values存储
     * @param key
     * @param values
     * @return
     */
    public Long sadd(String key,String... values){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key,values);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * set key-value获取
     * @param key
     * @return
     */
    public String spop(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.spop(key);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     *命令将 key 中储存的数字值增一
     * @param key
     * @return
     */
        public Long incr(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
