package com.kingja.distributedemo.controller;

import com.kingja.distributedemo.lock.RedisLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try (RedisLock redisLock = new RedisLock(redisTemplate, "redisKey", 30)) {
            if (redisLock.lock()) {
                log.info("进入锁");
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("方法执行完成");
        return "完成请求";
    }
}
