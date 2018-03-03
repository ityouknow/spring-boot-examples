package com.neo.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
  
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Test
  public void test() throws Exception {
    stringRedisTemplate.opsForValue().set("aaa", "111");
    stringRedisTemplate.opsForValue().set("bbb", "222");
    Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    
    Assert.assertEquals("222", stringRedisTemplate.opsForValue().get("bbb"));
  }

}
