package com.neo.entity;

import com.neo.entity.enums.UserSexEnum;
import lombok.Data;

@Data
public class UserEntity {

  private long id;
  private String userName;
  private String password;
  private UserSexEnum userSex;
  private String nickName;

  public UserEntity() {}

  public UserEntity(String userName, String passWord, UserSexEnum userSex, String nickName) {
    super();
    this.password = passWord;
    this.userName = userName;
    this.userSex = userSex;
    this.nickName = nickName;
  }
}
