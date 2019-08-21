package com.scnu.lotterysystem.config;

import com.scnu.lotterysystem.entity.Prize;
import com.scnu.lotterysystem.entity.Winner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/* *
* Redis的序列化配置
* 进行json数据的存取,防止亂碼
* */
@Configuration
public class MyRedisConfig {

    //prize的配置類
    @Bean
    public RedisTemplate<Object,Prize> prizeRedisTemplate
            (RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object,Prize> template=new RedisTemplate<Object,Prize>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Prize> serializer=new Jackson2JsonRedisSerializer<Prize>(Prize.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    //@Primary是設置為默認的管理器
    @Bean
    @Primary
    public RedisCacheManager prizeCacheManager(RedisTemplate<Object,Prize> prizeRedisTemplate){
        RedisCacheManager cacheManager=new RedisCacheManager(prizeRedisTemplate);
        //使用前綴，使用cachName作為前綴
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    //winner的配置類
    @Bean
    public RedisTemplate<Object,Winner> winnerRedisTemplate
    (RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object,Winner> template=new RedisTemplate<Object,Winner>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Winner> serializer=new Jackson2JsonRedisSerializer<Winner>(Winner.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    public RedisCacheManager winnerCacheManager(RedisTemplate<Object,Winner> winnerRedisTemplate){
        RedisCacheManager cacheManager=new RedisCacheManager(winnerRedisTemplate);
        //使用前綴，使用cachName作為前綴
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
