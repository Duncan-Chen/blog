package com.duncan.blog.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapCache {
	/**
	 * 默认存储1024个缓存
	 */
	private static final int DEFAULT_CACHE = 1024;
	private static final MapCache INS = new MapCache();
	
	public static MapCache single() {
		return INS;
	}
	
	/**
	 * 缓存容器
	 */
	private Map<String, CacheObject> cachePool;
	
	public MapCache() {
		this(DEFAULT_CACHE);
	}
	
	public MapCache(int cacheCount) {
		cachePool = new ConcurrentHashMap<>();
	}
	
	/**
	 * 读取一个缓存key
	 * @param key 缓存key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		CacheObject cacheObject = this.cachePool.get(key);
		if (null != cacheObject) {
			long cur = System.currentTimeMillis() / 1000;
			//未过期直接返回
			if (cacheObject.getExpired() < 0 || cacheObject.getExpired() > cur) {
				Object result = cacheObject.getValue();
				return (T) result;
			}
			//已过期直接删除
			if (cur > cacheObject.getExpired()) {
				this.cachePool.remove(key);
			}
			
		}
		return null;
	}
	
	/**
	 * 获取一个hash类型的缓存
	 * @param key
	 * @param field
	 * @return
	 */
	public <T> T hget(String key, String field) {
		key = key + ":" + field;
		return this.get(key);
	}
	
	/**
	 * 设置一个缓存并带过期时间
	 * @param key 缓存key
	 * @param value 缓存value
	 * @param expired 过期时间 单位秒
	 */
	public void set(String key, Object value, long expired) {
		expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
		if (this.cachePool.size() > 800) {
			this.cachePool.clear();
		}
		CacheObject cacheObject = new CacheObject(key, value, expired);
		this.cachePool.put(key, cacheObject);
	}
	
	/**
	 * 设置一个不过期的缓存
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		this.set(key, value, -1);
	}
	
	/**
	 * 设置一个hash缓存并带过期时间
	 * @param key
	 * @param field
	 * @param value
	 * @param expired
	 */
	public void hset(String key, String field, Object value, long expired) {
		key = key + ":" + field;
		expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
		CacheObject cacheObject = new CacheObject(key, value, expired);
		this.cachePool.put(key, cacheObject);
	}
	
	/**
	 * 设置不过期的hash缓存
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(String key, String field, Object value) {
		this.hset(key, field, value, -1);
	}
	
	/**
	 * 根据key删除缓存
	 * @param key
	 */
	public void del(String key) {
		this.cachePool.remove(key);
	}
	
	public void clean() {
		this.cachePool.clear();
	}
	
	/**
	 * 根据key和field删除缓存
	 * @param key
	 * @param field
	 */
	public void del(String key, String field) {
		key = key + ":" + field;
		this.cachePool.remove(key);
	}
	
	static class CacheObject {
		private String key;
		private Object value;
		private long expired;
		
		public CacheObject(String key, Object value, long expired) {
			this.key = key;
			this.value = value;
			this.expired = expired;
		}

		public String getKey() {
			return key;
		}

		public Object getValue() {
			return value;
		}

		public long getExpired() {
			return expired;
		}
	}
	
}
