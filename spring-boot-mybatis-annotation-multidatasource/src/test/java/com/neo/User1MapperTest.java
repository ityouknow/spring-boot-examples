package com.neo;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.neo.entity.UserEntity;
import com.neo.entity.enums.UserSexEnum;
import com.neo.mapper.datasource1.User1Mapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User1MapperTest {

  @Autowired
  private User1Mapper userMapper;

  @Test
  public void testInsert() throws Exception {
    userMapper.insert(new UserEntity("d1.aa", "a123456", UserSexEnum.MAN, "db1"));
    userMapper.insert(new UserEntity("d1.bb", "b123456", UserSexEnum.WOMAN, "db1"));
    userMapper.insert(new UserEntity("d1.cc", "b123456", UserSexEnum.WOMAN, "db1"));

    Assert.assertTrue(userMapper.getAll().size()>3);
  }

  @Test
  public void testQuery() throws Exception {
    List<UserEntity> users = userMapper.getAll();
    if (users == null || users.size() == 0) {
      System.out.println("is null");
    } else {
      System.out.println(users.size());
    }
  }


  @Test
  public void testUpdate() throws Exception {
    
    List<UserEntity> list = userMapper.getAll();
    if (list.size()>0) {return;}
    
    UserEntity user = list.get(0);
    System.out.println(user.toString());
    user.setNickName("neo");
    userMapper.update(user);
    Assert.assertTrue(("neo".equals(userMapper.getOne(user.getId()).getNickName())));
  }

}
