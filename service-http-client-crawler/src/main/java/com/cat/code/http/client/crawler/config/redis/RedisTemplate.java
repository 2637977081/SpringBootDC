package com.cat.code.http.client.crawler.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class RedisTemplate {
	@Autowired
	private JedisPool jedisPool;
	
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
	
	public String brpop(String key, int timeout) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			java.util.List<String> values = jedis.brpop(timeout, key);
			if (values == null || values.size() != 2) {
				return null;
			} 
			return values.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	
	
	public Long llen(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long len = jedis.llen(key);
			return len == null ? 0l : len;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0l;
	}
	
	public Boolean sismember(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Boolean exist = jedis.sismember(key, member);
			return exist;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	public Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<String> members = jedis.smembers(key);
			return members;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

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
}
