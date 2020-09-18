package com.kingja.distributedemo.controller;

import com.kingja.distributedemo.dao.DistributeLockMapper;
import com.kingja.distributedemo.model.DistributeLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DistributeController {

    private Lock mLock = new ReentrantLock();

    @Autowired
    DistributeLockMapper distributeLockMapper;

    @GetMapping("singleLock")
    public String singleLock() {
        log.info("线程："+Thread.currentThread().getName()+"进去方法");
        mLock.lock();
        log.info("线程："+Thread.currentThread().getName()+"进去锁");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mLock.unlock();
        return "完成请求";
    }

//    @Transactional(rollbackFor = Exception.class)
    @GetMapping("distributeLock")
    public String distributeLock() throws Exception {
        log.info("线程："+Thread.currentThread().getName()+"进去方法");
        DistributeLock distributeLock = distributeLockMapper.selectDistributeLock("demo");
        if (distributeLock == null) {
            throw new Exception("分布式锁不存在");
        }
        log.info("线程："+Thread.currentThread().getName()+"进去锁");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "完成请求";
    }
}
