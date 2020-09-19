package com.kingja.distributedemo.service;

import com.kingja.distributedemo.lock.RedisLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2020/9/19 0019 上午 11:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Service
@Slf4j
public class SchedulerService {
    @Autowired
    RedisTemplate redisTemplate;


    @Scheduled(cron = "0/5 * * * * ?")
    public void sendSms() {
        try (RedisLock  lock = new RedisLock(redisTemplate, "autoSms", 30)){
            if (lock.lock()) {
                log.info("发送短信");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
