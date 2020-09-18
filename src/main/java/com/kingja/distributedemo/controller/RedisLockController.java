package com.kingja.distributedemo.controller;

import com.kingja.distributedemo.dao.DistributeLockMapper;
import com.kingja.distributedemo.lock.RedisLock;
import com.kingja.distributedemo.model.DistributeLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStreamCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2020/9/18 0018 上午 10:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RestController
@Slf4j
public class RedisLockController {


    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("redisLock")
    public String redisLock() {
        log.info("进入方法");
        String key="redisKey";
        String value= UUID.randomUUID().toString();

        RedisLock redisLock = new RedisLock(redisTemplate, key, 15000);
        boolean lock = redisLock.lock();

        log.info("锁状态:"+lock);
        if (lock) {
            log.info("进入锁");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放redis锁
                boolean unlockResult = redisLock.unlock();
                log.info("释放锁的结果："+unlockResult);
            }
        }
        log.info("方法执行完成");
        return "完成请求";
    }
}
