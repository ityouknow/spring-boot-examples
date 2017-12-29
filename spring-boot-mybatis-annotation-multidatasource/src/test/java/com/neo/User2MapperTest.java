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
import com.neo.mapper.datasource2.User2Mapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User2MapperTest {

  @Autowired
  private User2Mapper userMapper;

  @Test
  public void testInsert() throws Exception {
    userMapper.insert(new UserEntity("db2.aa1", "a123456", UserSexEnum.MAN, "db2"));
    userMapper.insert(new UserEntity("db2.bb1", "b123456", UserSexEnum.WOMAN, "db2"));
    userMapper.insert(new UserEntity("db2.cc1", "b123456", UserSexEnum.WOMAN, "db2"));

    Assert.assertTrue(userMapper.getAll().size() > 3);
  }

  @Test
  public void testQuery() throws Exception {
    List<UserEntity> users = userMapper.getAll();
    if (users == null || users.size() == 0) {
      System.out.println("is null");
    } else {
      System.out.println(users.toString());
    }
  }


  @Test
  public void testUpdate() throws Exception {
    UserEntity user = userMapper.getOne(30);
    System.out.println(user.toString());
    user.setNickName("neo");
    userMapper.update(user);
    Assert.assertTrue(("neo".equals(userMapper.getOne(30).getNickName())));
  }

}
