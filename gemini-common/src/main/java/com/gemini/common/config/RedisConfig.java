package com.gemini.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author 小明不读书
 * @date 2018-11-07
 */
@Configuration
public class RedisConfig {

    /**
     * 默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
     * 1、将数据以json的方式保存
     * (1)自己将对象转为json
     * (2)redisTemplate默认的序列化规则；改变默认的序列化规则；
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> objectRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> ser = new Jackson2JsonRedisSerializer<Object>(Object.class);
        template.setDefaultSerializer(ser);
        return template;
    }


    //CacheManagerCustomizers可以来定制缓存的一些规则
//    @Primary  //将某个缓存管理器作为默认的
//    @Bean
//    public RedisCacheManager redisCacheManager(RedisTemplate<Object, Object> objectRedisTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(objectRedisTemplate);
//
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//
//        return cacheManager;
//    }
//
//    @Bean
//    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Role> deptRedisTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
//        //key多了一个前缀
//
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//
//        return cacheManager;
//    }


}
