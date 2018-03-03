package com.neo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import com.neo.entity.UserEntity;
import com.neo.entity.enums.UserSexEnum;
import com.neo.mapper.datasource1.User1Mapper;
import com.neo.mapper.datasource2.User2Mapper;
import com.neo.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

  @Autowired
  private UserService userService;

  @Autowired
  private User1Mapper userMapper1;

  @Autowired
  private User2Mapper userMapper2;


  @Test
  public void testCopy1to2() {

    UserEntity user = new UserEntity("d1.aa", "a123456", UserSexEnum.MAN, "hello world");
    userMapper1.insert(user);
    System.out.println(user.getId());

    userService.copyPropertyFrom1to2(user.getId(), false);

    Assert.isNull(userMapper1.getOne(user.getId()), user.getId() + "shoud be deleted!");
    Assert.notNull(userMapper2.getOne(user.getId()), user.getId() + "shoud be copied!");

  }


  @Test
  public void testCopyRollback1to2() {

    UserEntity user = new UserEntity("d1.aa", "a123456", UserSexEnum.MAN, "hello world");
    userMapper1.insert(user);
    System.out.println(user.getId());

    try {
      userService.copyPropertyFrom1to2(user.getId(), true);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Assert.notNull(userMapper1.getOne(user.getId()), user.getId() + "shoud not be deleted!");
    Assert.isNull(userMapper2.getOne(user.getId()), user.getId() + "shoud not be copied!");
  }
  
  
  @Test
  public void testCopy2to1() {

    UserEntity user = new UserEntity("d1.aa", "a123456", UserSexEnum.MAN, "hello world");
    userMapper2.insert(user);
    System.out.println(user.getId());

    userService.copyPropertyFrom2to1(user.getId(), false);

    Assert.isNull(userMapper2.getOne(user.getId()), user.getId() + "shoud be deleted!");
    Assert.notNull(userMapper1.getOne(user.getId()), user.getId() + "shoud be copied!");

  }


  @Test
  public void testCopyRollback2to1() {

    UserEntity user = new UserEntity("d1.aa", "a123456", UserSexEnum.MAN, "hello world");
    userMapper2.insert(user);
    System.out.println(user.getId());

    try {
      userService.copyPropertyFrom2to1(user.getId(), true);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.notNull(userMapper2.getOne(user.getId()), user.getId() + "shoud not be deleted!");
    Assert.isNull(userMapper1.getOne(user.getId()), user.getId() + "shoud not be copied!");

  }
}
