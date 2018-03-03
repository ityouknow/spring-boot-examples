package com.neo.mapper;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.neo.entity.UserEntity;
import com.neo.enums.UserSexEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

  @Autowired
  private UserMapper UserMapper;

  @Test
  public void testInsert() throws Exception {
    UserMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN, "cc"));
    UserMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN, "dd"));
    UserMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN, "dd"));

    Assert.assertTrue(UserMapper.getAll().size() > 3);
  }

  @Test
  public void testQuery() throws Exception {
    List<UserEntity> users = UserMapper.getAll();
    System.out.println(users.toString());
  }


  @Test
  public void testUpdate() throws Exception {

    List<UserEntity> list = UserMapper.getAll();
    
    
    if (list != null) {
      long id = list.get(list.size() - 1).getId();
      UserEntity user = UserMapper.getOne(id);
      System.out.println(user.toString());
      user.setNickName("neo");
      UserMapper.update(user);
      Assert.assertTrue(("neo".equals(UserMapper.getOne(id).getNickName())));
    } else {
      System.out.println("no data");
    }

  }

}
