package com.neo.repository;

import com.neo.model.User;
import com.neo.model.UserDetail;
import com.neo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserDetailRepository extends JpaSpecificationExecutor<UserDetail>,JpaRepository<UserDetail, Long>  {

    UserDetail  findByHobby(String hobby);

    @Query("select u.userName as userName, u.email as email, d.introduction as introduction , d.hobby as hobby from User u , UserDetail d " +
            "where u.id=d.userId  and  d.hobby = ?1 ")
    List<UserInfo> findUserInfo(String hobby);
}