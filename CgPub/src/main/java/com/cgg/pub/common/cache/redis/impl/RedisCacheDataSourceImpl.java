package com.cgg.pub.common.cache.redis.impl;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.cgg.pub.common.cache.redis.RedisDataSource;
import com.cgg.pub.common.util.ProtoStuffSerializerUtil;

@Component("redisCacheDataSource")
public class RedisCacheDataSourceImpl implements RedisDataSource {

//	private static final Logger log = LoggerFactory.getLogger(RedisCacheDataSourceImpl.class);

	
//	@Resource(name="stringRedisTemplate")
//	protected RedisTemplate<Serializable, Serializable> stringRedisTemplate;
	
	@Resource(name="redisTemplate")
	protected RedisTemplate<Serializable, Serializable> redisTemplate;
	@Override
	public <T> boolean putCache(String key, T obj) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializer(obj);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection){
				return connection.setNX(bkey, bvalue);
			}
		});
		return result;
	}

	@Override
	public <T> boolean putCacheWithExpireTime(String key, T obj, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializer(obj);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
		return result;
	}
	@Override
	public <T> boolean putListCache(String key, List<T> objList) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(bkey, bvalue);
			}
		});
		return result;
	}
	@Override
	public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
		return result;
	}

	@Override
	public <T> T getCache(final String key, Class<T> targetClass) {
		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return ProtoStuffSerializerUtil.deserializer(result, targetClass);
	}
	@Override
	public <T> List<T> getListCache(final String key, Class<T> targetClass) {
		
		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		return ProtoStuffSerializerUtil.deserializeList(result, targetClass);
	}
	@Override
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void deleteCacheWithPattern(String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		redisTemplate.delete(keys);
	}

	@Override
	public void clearCache() {
		deleteCacheWithPattern(CAHCENAME+"|*");
	}
	
	
////	@Autowired
//    private ShardedJedisPool shardedJedisPool;
//	
//	@Override
//	public ShardedJedis getRedisClient() {
//		try {
//            ShardedJedis shardJedis = shardedJedisPool.getResource();
//            return shardJedis;
//        } catch (Exception e) {
//            log.error("getRedisClent error", e);
//        }
//        return null;
//	}
//
//	@Override
//	public void returnResource(ShardedJedis shardedJedis) {
//		shardedJedisPool.returnResource(shardedJedis);
//	}
//
//	@Override
//	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
//		if (broken) {
//            shardedJedisPool.returnBrokenResource(shardedJedis);
//        } else {
//            shardedJedisPool.returnResource(shardedJedis);
//        }
//	}

}
