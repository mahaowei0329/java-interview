package com.mahw;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RedisLock {

    private static String Lock_key = "lalala";

    public static void main(String[] args) {
        RedisTemplate redisTemplate = new RedisTemplate();

        Boolean mahw = redisTemplate.opsForValue().setIfAbsent(Lock_key, "mahw", 10, TimeUnit.SECONDS);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                //定时重置过期时间
                redisTemplate.opsForValue().set(Lock_key, "mahw", 10, TimeUnit.SECONDS);
            }
        }, 3, TimeUnit.SECONDS);
        try {
            if(mahw){
                //设置成功证明拿到锁，则执行业务逻辑
                //业务逻辑
            }else{
                //设置失败证明有其他节点拿到了锁，则返回/等待
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放锁
            redisTemplate.delete(Lock_key);
            //任务执行完成之后不再执行重置
            scheduledExecutorService.shutdownNow();
        }
    }
}
