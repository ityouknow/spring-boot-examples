package com.neo.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @GeneratedValue(strategy = GenerationType.IDENTITY) 采用数据库自增方式，oracle不支持此种方式
 * @GeneratedValue(strategy = GenerationType.SEQUENCE) 采用数据库sequence序列机制生成主键。mysql不支持此种方式。
 * @GeneratedValue(strategy = GenerationType.AUTO)     主键增长方式由数据库自动选择，使用表存储生成的主键，可以跨数据库
 * Entity中不映射成列的字段得加@Transient 注解，不加注解也会映射成列
 * 
 * @Entity 关系型数据库支持类型、声明@Document 为mongodb支持类型
 */
@Entity
@Table(name="userJpa")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true,name = "userName")
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int age;
    
	private Date birthDay;

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }
    
    public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}
