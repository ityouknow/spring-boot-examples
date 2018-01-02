package com.neo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neo.entity.UserEntity;
import com.neo.mapper.datasource1.User1Mapper;
import com.neo.mapper.datasource2.User2Mapper;

@Service
public class UserService {

  @Autowired
  private User1Mapper user1Mapper;

  @Autowired
  private User2Mapper user2Mapper;

  @Transactional
  public boolean copyPropertyFrom1to2(long id, boolean raiseError) {

    UserEntity user1;
    UserEntity user2;
    user1 = user1Mapper.getOne(id);
    user2 = user2Mapper.getOne(id);

    if (user1 == null) {
      return false;
    }

    if (user2 == null) {
      user2Mapper.insertWithId(user1);
    } else {
      user2Mapper.update(user1);
    }
    
    if (raiseError) {
      System.out.println(user1);
      throw new RuntimeException("oooo");
    }

    user1Mapper.delete(id);
    return true;
  }
  
  
  
  @Transactional
  public boolean copyPropertyFrom2to1(long id, boolean raiseError) {

    UserEntity user1;
    UserEntity user2;
    user1 = user1Mapper.getOne(id);
    user2 = user2Mapper.getOne(id);

    if (user2 == null) {
      return false;
    }

    if (user1 == null) {
      user1Mapper.insertWithId(user2);
    } else {
      user1Mapper.update(user2);
    }
    
    if (raiseError) {
      System.out.println(user2);
      throw new RuntimeException("oooo");
    }

    user2Mapper.delete(id);
    return true;
  }

}
