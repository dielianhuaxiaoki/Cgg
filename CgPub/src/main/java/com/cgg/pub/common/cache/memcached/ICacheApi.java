package com.cgg.pub.common.cache.memcached;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

public interface ICacheApi {
	/**
	 * 
	 * add object into cache
	 * 
	 * @param key
	 * @param obj
	 * @param expire
	 *            expire time (s)
	 * @return
	 * @throws MemcachedException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public boolean add(String key, int expire, Object obj)
			throws MemcachedException, TimeoutException, InterruptedException;

	/**
	 * 
	 * get object from cache
	 * 
	 * @param key
	 * @return
	 * @throws MemcachedException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public Object get(String key) throws MemcachedException, TimeoutException, InterruptedException;

	/**
	 * 
	 * delete object from cache
	 * 
	 * @param key
	 * @return
	 * @throws MemcachedException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public boolean delete(String key) throws MemcachedException, TimeoutException, InterruptedException;

	/**
	 * 
	 * replace object from cache
	 * 
	 * @param key
	 * @param obj
	 * @param expire
	 *            expire time (s)
	 * @return
	 * @throws MemcachedException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public boolean replace(String key, int expire, Object obj)
			throws MemcachedException, TimeoutException, InterruptedException;

	/**
	 * 
	 * remove all cache object
	 */
	public void removeAll() throws InterruptedException, MemcachedException, TimeoutException;

	/**
	 * get object from cache and update expire time
	 * 
	 * @param key
	 * @param expire
	 *            expire time (s)
	 * @return
	 * @throws InterruptedException
	 * @throws MemcachedException
	 * @throws TimeoutException
	 */
	public Object getAndTouch(String key, int expire) throws InterruptedException, MemcachedException, TimeoutException;

	/**
	 * update cache expire time
	 * 
	 * @param key
	 * @param expire
	 *            expire time (s)
	 * @return
	 * @throws InterruptedException
	 * @throws MemcachedException
	 * @throws TimeoutException
	 */
	public boolean touch(String key, int expire) throws InterruptedException, MemcachedException, TimeoutException;
}
