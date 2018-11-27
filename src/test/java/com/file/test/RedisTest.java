package com.file.test;

import com.file.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zkm on 2018-09-27.
 * @version 1.0
 */
@Component
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        // set
        stringRedisTemplate.opsForValue().set("key", "value");
        // get
        String value = stringRedisTemplate.opsForValue().get("key");
        Assert.assertEquals("value", value);
    }

    @Test
    public void testObj() {
        User user = new User();
        user.setAge(18);
        user.setUserName("king");
        user.setCupSize("C");
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        // set
        operations.set("user", "zhangkaiming");
        // get
        String username = operations.get("user");
//        Assert.assertEquals(user.getAge(), user2.getAge());
//        Assert.assertEquals(user.getUserName(), user2.getUserName());
//        Assert.assertEquals(user.getCupSize(), user2.getCupSize());
        Assert.assertEquals("zhangkaiming", username);
    }



}
