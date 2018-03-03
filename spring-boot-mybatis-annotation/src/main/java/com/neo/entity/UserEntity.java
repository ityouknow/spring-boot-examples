package com.neo.entity;

import com.neo.enums.UserSexEnum;
import lombok.Data;

@Data
public class UserEntity {

  private long id;
  private String userName;
  private String passWord;
  private UserSexEnum userSex;
  private String nickName;

  public UserEntity() {}

  public UserEntity(String userName, String passWord, UserSexEnum userSex, String nickName) {
    super();
    this.passWord = passWord;
    this.userName = userName;
    this.userSex = userSex;
    this.nickName = nickName;
  }
}
