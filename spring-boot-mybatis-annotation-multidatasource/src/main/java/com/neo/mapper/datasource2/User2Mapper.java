package com.neo.mapper.datasource2;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import com.neo.entity.UserEntity;
import com.neo.entity.enums.UserSexEnum;

@Mapper
public interface User2Mapper {
  @Select("SELECT * FROM users")
  @Results(id = "userEntity", value= {@Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
      @Result(property = "nickName", column = "nick_name"),
      @Result(property = "password", column = "passWord")})
  List<UserEntity> getAll();

  @Select("SELECT * FROM users WHERE id = #{id}")
  @ResultMap("userEntity")
  UserEntity getOne(long id);

  @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{password}, #{userSex})")
  @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false,
  resultType = int.class)
  void insert(UserEntity user);
  
  @Insert("INSERT INTO users(id, userName,passWord,user_sex, nick_name) "
      + "VALUES(#{id}, #{userName}, #{password}, #{userSex}, #{nickName})")
  void insertWithId(UserEntity user);

  @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName}, passWord=#{password} WHERE id =#{id}")
  void update(UserEntity user);

  @Delete("DELETE FROM users WHERE id =#{id}")
  void delete(long id);
}
