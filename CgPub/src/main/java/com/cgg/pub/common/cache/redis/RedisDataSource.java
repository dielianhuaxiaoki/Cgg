package com.cgg.pub.common.cache.redis;

import java.util.List;

public interface RedisDataSource {
	
	public final static String CAHCENAME="cache";//缓存名
	public final static int CAHCETIME=60;//默认缓存时间
	
	//put data
    public <T> boolean putCache(String key, T obj);
    public <T> boolean putCacheWithExpireTime(String key, T obj, long expireTime);
    public <T> boolean putListCache(String key, List<T> objList);
    public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, long expireTime);
    
    //get data
    public <T> T getCache(String key, Class<T> targetClass);
    public <T> List<T> getListCache(String key, Class<T> targetClass);
   
    //delete data
    public void deleteCache(String key);
    public void deleteCacheWithPattern(String pattern);
    
    //clear
    public void clearCache();
}
