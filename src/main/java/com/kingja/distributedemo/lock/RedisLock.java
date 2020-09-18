package com.kingja.distributedemo.lock;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2020/9/18 0018 下午 5:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Slf4j
public class RedisLock implements AutoCloseable {

    private RedisTemplate redisTemplate;
    private String key;
    private String value;
    private int expirTime;

    public RedisLock(RedisTemplate redisTemplate, String key, int expirTime) {
        this.redisTemplate = redisTemplate;
        this.key = key;
        this.expirTime = expirTime;
    }

    public boolean lock() {
        RedisCallback<Boolean> redisCallback = new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //设置NX
                RedisStringCommands.SetOption setOption = RedisStringCommands.SetOption.ifAbsent();
                //设置过期时间
                Expiration expiration = Expiration.seconds(expirTime);
                //序列化key
                byte[] redisKey = redisTemplate.getKeySerializer().serialize(key);
                //序列化value
                byte[] redisValue = redisTemplate.getKeySerializer().serialize(value);
                //执行setnx操作
                Boolean result = redisConnection.set(redisKey, redisValue, expiration, setOption);
                return result;
            }
        };
        return (Boolean) redisTemplate.execute(redisCallback);
    }

    public boolean unlock() {
        //释放redis锁
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        RedisScript redisScript = RedisScript.of(script, Boolean.class);
        List<String> keys = Arrays.asList(key);
        Boolean unlockResult = (Boolean) redisTemplate.execute(redisScript, keys, value);
        log.info("释放锁的结果：" + unlockResult);
        return unlockResult;
    }

    @Override
    public void close() throws Exception {
        unlock();
    }
}
