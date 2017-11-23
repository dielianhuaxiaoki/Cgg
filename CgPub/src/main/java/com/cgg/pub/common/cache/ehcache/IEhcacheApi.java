package com.cgg.pub.common.cache.ehcache;

import java.util.List;
import java.util.Map;

public interface IEhcacheApi {
	public Object get(String paramString);

	public boolean cache(String paramString, Object paramObject);

	public boolean delete(String paramString);

	public Map<String, Object> get(List<String> paramList);
}
