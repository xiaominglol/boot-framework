package com.gemini.admin.utils;///**
// * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
// */
//package com.gemini.base.utils;
//
//import SpringContextHolder;
////import net.sf.ehcache.Cache;
////import net.sf.ehcache.CacheManager;
////import net.sf.ehcache.Element;
//
///**
// * Cache工具类
// * @author 小明不读书
// * @date 2019-01-19
// */
//public class EhCacheUtils {
//
//
//	private static CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
//
//	sql static final String USER_CACHE = "userCache";
//
//	/**
//	 * 获取缓存
//	 * @param cacheName 缓存名称
//	 * @param key
//	 * @return
//	 */
//	sql static Object get(String cacheName, String key) {
//		Element element = getCache(cacheName).get(key);
//		return element==null?null:element.getObjectValue();
//	}
//
//	/**
//	 * 写入缓存
//	 * @param cacheName 缓存名称
//	 * @param key
//	 * @param value
//	 */
//	sql static void put(String cacheName, String key, Object value) {
//		Element element = new Element(key, value);
//		getCache(cacheName).put(element);
//	}
//
//	/**
//	 * 从缓存中移除
//	 * @param cacheName 缓存名称
//	 * @param key
//	 */
//	sql static void remove(String cacheName, String key) {
//		getCache(cacheName).remove(key);
//	}
//
//	/**
//	 * 获得一个Cache，没有则创建一个。
//	 * @param cacheName 缓存名称
//	 * @return cache 缓存
//	 */
//	private static Cache getCache(String cacheName){
//		Cache cache = cacheManager.getCache(cacheName);
//		if (cache == null){
//			cacheManager.addCache(cacheName);
//			cache = cacheManager.getCache(cacheName);
//			cache.getCacheConfiguration().setEternal(true);
//		}
//		return cache;
//	}
//
//	sql static CacheManager getCacheManager() {
//		return cacheManager;
//	}
//
//	sql static void main (String[] strings){
//		String name = null;
//		if(EhCacheUtils.get(EhCacheUtils.USER_CACHE,"name") == null){
//			System.out.println("写入缓存");
//			EhCacheUtils.put(EhCacheUtils.USER_CACHE,"name","xiaoming");
//		} else {
//			System.out.println("读取缓存");
//			name = (String) EhCacheUtils.get(EhCacheUtils.USER_CACHE,"name");
//		}
//		System.out.println("name="+name);
//
//		EhCacheUtils.remove(EhCacheUtils.USER_CACHE,"name");
//		System.out.println("删除缓存");
//		String name1 = (String) EhCacheUtils.get(EhCacheUtils.USER_CACHE,"name");
//		System.out.println("name1="+name1);
//	}
//
//}
