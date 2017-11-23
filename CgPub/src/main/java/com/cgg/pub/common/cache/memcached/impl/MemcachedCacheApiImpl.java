package com.cgg.pub.common.cache.memcached.impl;

import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cgg.pub.common.cache.memcached.ICacheApi;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

//@Component("cacheApi")
public class MemcachedCacheApiImpl implements ICacheApi {

	@Resource(name="memcachedClient")
	protected MemcachedClient memcachedClient;
	
	public boolean add(String key, int expire, Object obj)
			throws MemcachedException, TimeoutException, InterruptedException {
		return memcachedClient.set(key, expire, obj);
	}

	public Object get(String key) throws MemcachedException, TimeoutException, InterruptedException {
		return memcachedClient.get(key);
	}

	public boolean delete(String key) throws MemcachedException, TimeoutException, InterruptedException {
		return memcachedClient.delete(key);
	}

	public boolean replace(String key, int expire, Object obj)
			throws MemcachedException, TimeoutException, InterruptedException {
		return memcachedClient.replace(key, expire, obj);
	}

	public void removeAll() throws InterruptedException, MemcachedException, TimeoutException {
		// TODO Auto-generated method stub
		memcachedClient.flushAll();
	}

	public Object getAndTouch(String key, int expire)
			throws InterruptedException, MemcachedException, TimeoutException {
		// TODO Auto-generated method stub
		return memcachedClient.getAndTouch(key, expire);
	}

	public boolean touch(String key, int expire) throws InterruptedException, MemcachedException, TimeoutException {
		// TODO Auto-generated method stub
		return memcachedClient.touch(key, expire);
	}

}
