package com.cgg.pub.common.cache.ehcache.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cgg.pub.common.cache.ehcache.IEhcacheApi;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheManagementHelper implements IEhcacheApi {

//	private static final Logger log = Logger.getLogger(EhcacheManagementHelper.class);
	protected static CacheManager manager;
	protected static String cacheName = "ehcacheSample";
	protected static Cache cache;
	protected static EhcacheManagementHelper instance;

	static {
		manager = CacheManager.create(EhcacheManagementHelper.class.getResource("/cache/ehcache.xml"));
		System.out.println(manager.getConfiguration().getDiskStoreConfiguration().getPath());

		manager.addCache(cacheName);
		cache = manager.getCache(cacheName);
	}

	public static EhcacheManagementHelper getInstance() {
		if (instance == null) {
			instance = new EhcacheManagementHelper();
		}
		return instance;
	}

	public Object get(String key) {
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			}
			return null;
		}

		return null;
	}

	public boolean delete(String key) {
		return cache.remove(key);
	}

	public boolean cache(String key, Object value) {
		if (cache != null) {
			cache.put(new Element(key, value));
			return true;
		}
		return false;
	}

	public Map<String, Object> get(List<String> keys) {
		Map<String, Object> results = new LinkedHashMap<String, Object>();
		if (cache != null) {
			for (String key : keys) {
				Element element = cache.get(key);
				if (element != null) {
					results.put(key, element.getObjectValue());
				}

			}
		} else {
//			log.fatal("No cache instance.");
		}

		return results;
	}

}
