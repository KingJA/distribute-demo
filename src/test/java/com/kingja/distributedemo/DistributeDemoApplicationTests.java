package com.kingja.distributedemo;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DistributeDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testRessionLock() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.1.107:6379").setPassword("wzy1gqqbuu");
        RedissonClient redisson= Redisson.create(config);
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
    }

}
