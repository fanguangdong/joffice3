/*package com.ocsoft.oa.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ocsoft.core.util.ApplicationContextHolder;
import com.ocsoft.oa.service.system.IReferenceTableService;
import com.ocsoft.oa.vo.system.ReferenceTable;

public class ReferenceCache
{
	private final static Logger logger = Logger.getLogger(ReferenceCache.class);
	
	private static IReferenceTableService refTableService = (IReferenceTableService)ApplicationContextHolder.getApplicationContext(ApplicationContextHolder.AppCtx.OA_CONTEXT).getBean("refTableService");
	private static Map<String, ReferenceCache> cacheMap = new HashMap<String, ReferenceCache>();
	private Map<String,Object> map = new HashMap<String,Object>();
	private String key;
	private String NO_ARGUMENT_KEY = "JOF";
	
	
	
	
	
	public static synchronized ReferenceCache getReferenceCache(String key) {
		key = key.toLowerCase();
		final long start = System.currentTimeMillis();
		ReferenceCache cache =  cacheMap.get(key);
		final boolean containsCache = (cache != null);
		if (!containsCache) {
			cache = new ReferenceCache();
			cache.key = key;
			cacheMap.put(key, cache);
			cache.map = Collections.synchronizedMap(cache.map);
		}
		
		logger.debug("Time taken to execute ReferenceCache.getReferenceCache(String key):Key=" + key + ":containsCache:" + containsCache + ":"
					+ (System.currentTimeMillis() - start));

		return cache;
	}
	
	
	public Collection get(String key){ return (Collection)map.get(key); }
	public Collection get(){ return (Collection)map.get(NO_ARGUMENT_KEY); }
	public Collection add(Collection collection)
	{
		map.put(NO_ARGUMENT_KEY, collection);
		return collection;
	}

	public Collection add(String key, Collection collection)
	{
		map.put(key, collection);
		return collection;
	}
	
	public static Collection getRefs(String key)
	{
		ReferenceCache cache = getReferenceCache("key");
		Collection result = cache.get();
		if (result == null)
		{
			result = cache.add(refTableService.getRefList(key));
		}
		return result;
	}
	
	
	
	
	public IReferenceTableService getRefTableService()
	{
		return refTableService;
	}
	public void setRefTableService(IReferenceTableService refTableService)
	{
		this.refTableService = refTableService;
	}
	
	
	
	

	
	
	

}
*/