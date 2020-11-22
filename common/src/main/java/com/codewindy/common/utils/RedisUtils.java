package com.codewindy.common.utils;/*
package com.jkwindy.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

*/
/**
 * @desc 分布式锁redisson
 * @author jkwindy@126.com
 * @date 2019-04-20 22:00
 *//*

@Component
@Slf4j
public class RedisUtils {
    @Autowired
    private RedissonClient redissonClient;

    public void lock(String key){
        RLock lock = redissonClient.getLock(key);
       try {
           lock.lock();
           //do your biz
       }catch (Exception e){
           log.info("e===={}",e);
           lock.unlock();
       }
    }

    public void lockExpire(String key,Long expireTime){
        RLock lock = redissonClient.getLock(key);
        lock.lock(expireTime, TimeUnit.MINUTES);
    }

    public void unlock(String key){
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }


    public static void main(String[] args) {
        String ss="1,2,3,4,5,6";
        String[] split = ss.split(",");
        String join = StringUtils.join(split,"-");
        String join1 = StringUtils.join(split);
        System.out.println("join1 = " + join1);
        System.err.println(join);

    }
}
*/
