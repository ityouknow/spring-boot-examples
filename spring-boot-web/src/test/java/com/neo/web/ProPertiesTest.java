package com.neo.web;

import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.common.collect.Maps;
import com.neo.util.NeoProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProPertiesTest {


  @Autowired
  private NeoProperties neoProperties;


  @Test
  public void getHello() throws Exception {
    Assert.assertEquals("欢迎", neoProperties.getTitle());
    Assert.assertEquals("Welcome 李志鹏", neoProperties.getDescription());
  }


  @Test
  public void testMap() throws Exception {
    Map<String, Long> orderMinTime = Maps.newLinkedHashMap();
    Long xx = orderMinTime.get("123");
    System.out.println("xx=" + xx);
  }

}
