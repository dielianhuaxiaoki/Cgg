package com.cgg.pub.common.cache.redis.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgg.pub.common.cache.redis.RedisDataSource;
import com.cgg.pub.common.util.ProtoStuffSerializerUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

//@Component("redisClusterCacheDataSource")
public class RedisClusterCacheDataSourceImpl implements RedisDataSource {

	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public <T> boolean putCache(String key, T obj) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializer(obj);
		return jedisCluster.set(bkey,bvalue) != null ? true : false;
	}

	@Override
	public <T> boolean putCacheWithExpireTime(String key, T obj, long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializer(obj);
		return jedisCluster.setex(bkey, (int)expireTime, bvalue) != null ? true : false;
	}

	@Override
	public <T> boolean putListCache(String key, List<T> objList) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
		return jedisCluster.set(bkey,bvalue) != null ? true : false;
	}

	@Override
	public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
		return jedisCluster.setex(bkey, (int)expireTime, bvalue) != null ? true : false;
	}

	@Override
	public <T> T getCache(String key, Class<T> targetClass) {
		byte[] result =jedisCluster.get(key.getBytes());
		if (result == null) {
			return null;
		}
		return ProtoStuffSerializerUtil.deserializer(result, targetClass);
	}

	@Override
	public <T> List<T> getListCache(String key, Class<T> targetClass) {
		byte[] result =jedisCluster.get(key.getBytes());
		if (result == null) {
			return null;
		}
		return ProtoStuffSerializerUtil.deserializeList(result, targetClass);
	}

	@Override
	public void deleteCache(String key) {
		jedisCluster.del(key);
	}

	@Override
	public void deleteCacheWithPattern(String pattern) {
		Set<String> keys =this.keys(pattern);
		for(String key:keys){
			jedisCluster.del(key);
		}
	}

	@Override
	public void clearCache() {
		deleteCacheWithPattern(CAHCENAME+"|*");
	}

	private Set<String> keys(String pattern){
		Set<String> keys = new HashSet<String>();
		Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
		for(String k : clusterNodes.keySet()){
			JedisPool jp = clusterNodes.get(k);
			Jedis connection = jp.getResource();
			try {
				keys.addAll(connection.keys(pattern));
			} catch(Exception e){
				e.printStackTrace();
			} finally{
				//用完一定要close这个链接！！！
				connection.close();
			}
		}
		return keys;
	}
}
