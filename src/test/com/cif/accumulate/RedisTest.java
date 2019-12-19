package com.cif.accumulate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description redis测试
 * @Author liuxincai
 * @create 2018-11-28-18:22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void dateTest() {
        LocalDateTime ldt2=LocalDateTime.of(2015, 10, 19, 13, 22,33);
        System.out.println(ldt2);
        System.out.println("LocalDateTime.now()="+LocalDateTime.now());
        System.out.println("new Date()="+new Date());
    }

    @Test
    public void stringRedisTemplateTest() {
        stringRedisTemplate.opsForValue().set("123", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
