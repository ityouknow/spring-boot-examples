package com.neo.dao.impl;

import com.mongodb.WriteResult;
import com.neo.dao.UserDao;
import com.neo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * Created by summer on 2017/5/5.
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     * @param user
     */
    @Override
    public void saveUser(UserEntity user) {
        mongoTemplate.save(user);
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    @Override
    public UserEntity findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        UserEntity user =  mongoTemplate.findOne(query , UserEntity.class);
        return user;
    }

    /**
     * 更新对象
     * @param user
     */
    @Override
    public int updateUser(UserEntity user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        //更新查询返回结果集的第一条
        WriteResult result =mongoTemplate.updateFirst(query,update,UserEntity.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return result.getN();
        else
            return 0;
    }

    /**
     * 删除对象
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserEntity.class);
    }
}
