package com.cn.zbx.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 本地缓存
 * 
 * @author zyw
 *
 */
public class CacheUtil {

	/**
	 * 缓存字段 使用Map<String,Object> 格式
	 */
	public static Map<String, String> cache = null;

	/**
	 * 读写锁
	 */
	private static ReadWriteLock rwlCache = new ReentrantReadWriteLock();

	/**
	 * 根据key获取缓存数据
	 * 
	 * @param key
	 * @return Object类型的数据
	 */
	public static String get(String key) {
		if (null == key) {
			return null;
		} else {
			CacheUtil.rwlCache.readLock().lock();
			try {
				String string = CacheUtil.getCache().get(key);
				if (null == string) {
					return null;
				} else {
					Map<String, Object> map = JsonUtil.jsonToMap(string);
					Integer time = (Integer) map.get("time"); // 时间戳
					Integer timeout = (Integer) map.get("timeout"); // 超时时间
					String value = (String) map.get("value"); // 值
					if (timeout < 0) {
						return value;
					} else {
						if ((System.currentTimeMillis() / 1000 - time) > timeout) {
							CacheUtil.del(key);
							return null;
						} else {
							return value;
						}
					}
				}
			} finally {
				CacheUtil.rwlCache.readLock().unlock();
			}
		}
	}

	/**
	 * 写入数据到缓存根据键值(重复key会覆盖)
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		if (null == key || null == value) {
		} else {
			CacheUtil.rwlCache.writeLock().lock();
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("value", value);
				map.put("time", System.currentTimeMillis() / 1000);
				map.put("timeout", -1);
				CacheUtil.getCache().put(key, JsonUtil.toJson(map));
			} finally {
				CacheUtil.rwlCache.writeLock().unlock();
			}
		}
	}

	/**
	 * 写入数据到缓存根据键值(重复key会覆盖)
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value, Integer s) {
		if (null == key || null == value) {
		} else {
			CacheUtil.rwlCache.writeLock().lock();
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("value", value);
				map.put("time", System.currentTimeMillis() / 1000);
				map.put("timeout", s);
				CacheUtil.getCache().put(key, JsonUtil.toJson(map));
			} finally {
				CacheUtil.rwlCache.writeLock().unlock();
			}
		}
	}

	public static boolean expire(String key, Integer s) {
		String string = CacheUtil.getCache().get(key);
		if (null == string) {
			return false;
		} else {
			CacheUtil.rwlCache.writeLock().lock();
			try {
				Map<String, Object> map = JsonUtil.jsonToMap(string);
				map.put("time", System.currentTimeMillis() / 1000);
				map.put("timeout", s);
				CacheUtil.getCache().put(key, JsonUtil.toJson(map));
				return true;
			} finally {
				CacheUtil.rwlCache.writeLock().unlock();
			}
		}
	}

	/**
	 * 查询缓存剩余有效时间
	 * 
	 * @param key
	 * @return  0:值不存在
	 * 			-1:不限时
	 */
	public static Integer ltt(String key) {
		String string = CacheUtil.getCache().get(key);
		if (null == string) {
			return 0;
		} else {
			CacheUtil.rwlCache.readLock().lock();
			try {
				Map<String, Object> map = JsonUtil.jsonToMap(string);
				Integer time = (Integer) map.get("time"); // 时间戳
				Integer timeout = (Integer) map.get("timeout"); // 超时时间
				if (timeout < 0) {
					return -1;
				} else {
					if ((System.currentTimeMillis() / 1000 - time) >= timeout) {
						CacheUtil.rwlCache.readLock().unlock();
						CacheUtil.del(key);
						CacheUtil.rwlCache.readLock().lock();
						return 0;
					} else {
						return (int) (timeout - (System.currentTimeMillis() / 1000 - time));
					}
				}
			} finally {
				CacheUtil.rwlCache.readLock().unlock();
			}
		}
	}

	/**
	 * 删除map中的指定key的值
	 * 
	 * @param mapKey
	 */
	public static void del(String key) {
		if (null == key) {
		} else {
			CacheUtil.rwlCache.writeLock().lock();
			try {
				System.out.println("del--------------------");
				CacheUtil.getCache().remove(key);
			} finally {
				CacheUtil.rwlCache.writeLock().unlock();
			}
		}
	}

	/**
	 * 获取所有数据从缓存 私有
	 * 
	 * @return Map<String,Object>
	 */
	private static Map<String, String> getCache() {
		if (null == CacheUtil.cache || CacheUtil.cache.equals("")) {
			CacheUtil.rwlCache.writeLock().lock();
			try {
				cache = new HashMap<String, String>();
			} finally {
				CacheUtil.rwlCache.writeLock().unlock();
			}
		}
		return cache;
	}

	/**
	 * 测试.demo
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		set("test", "testtest");

		System.out.println(get("test"));

		set("test10", "testtest10", 1);
		System.out.println(get("test10"));

		System.out.println(ltt("test10"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println(ltt("test10"));
		
	}
}
