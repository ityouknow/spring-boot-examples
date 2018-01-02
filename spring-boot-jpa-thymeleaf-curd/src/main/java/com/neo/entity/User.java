package com.neo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Column的name属性，只有配置如下方式下才能生效
 * spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
 * spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
 * 
 * @author lizhipeng
 * @date 2017-12-27 18:59:53
 */

@Entity
@Table(name = "users")
@Data
public class User {
  @Id
  @GeneratedValue
  private long id;

  @Column(name="userName")
  private String userName;

  @Column(name = "passWord")
  private String password;

  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "user_sex")
  private String userSex;
}
