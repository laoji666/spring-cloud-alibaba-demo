package com.laoji.business.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisClientTests {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void setTest(){
        redisTemplate.opsForValue().set("name","laoji");

    }

    @Test
    public void getTest(){
        //redis自增长键
        RedisAtomicLong entityIdCounter = new RedisAtomicLong("testKey", redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();


        System.out.println(increment);
    }
}
