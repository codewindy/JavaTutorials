package com.codewindy.mongodb.config;/*
package com.jkwindy.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

*/
/**
 * @author jkwindy@126.com
 * @date 2019-04-20 21:42
 * //指定编码，默认编码为org.redisson.codec.JsonJacksonCodec
 * //之前使用的spring-data-redis，用的客户端jedis，编码为org.springframework.data.redis.serializer.StringRedisSerializer
 * //改用redisson后为了之间数据能兼容，这里修改编码为org.redisson.client.codec.StringCodec
 * config.setCodec(new org.redisson.client.codec.StringCodec());
 *//*

@Configuration
public class RedissonConfig {
    */
/**
     * redissonClient 每次需要主动关闭连接
     * @return
     * @throws IOException
     *//*

    @Bean(destroyMethod="shutdown")
    public RedissonClient redissonClient() throws IOException {
        RedissonClient redissonClient = Redisson.create(
                Config.fromYAML(new ClassPathResource("redisson.yml").getInputStream()));
        return redissonClient;
    }

}
*/
