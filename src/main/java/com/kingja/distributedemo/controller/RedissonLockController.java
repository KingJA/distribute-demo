package com.kingja.distributedemo.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2020/9/18 0018 上午 10:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RestController
@Slf4j
public class RedissonLockController {

    @Autowired
    RedissonClient redisson;

    @GetMapping("redissonLock")
    public String redisLock() {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://192.168.1.107:6379")
//                .setPassword("wzy1gqqbuu");
//        RedissonClient redisson= Redisson.create(config);
        RLock rLock = redisson.getLock("order");
        log.info("我进入了方法");
        try {
            rLock.lock(30, TimeUnit.SECONDS);
            log.info("我获得了锁");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info("我释放了锁");
            rLock.unlock();
        }
        return "完成请求";
    }
}
