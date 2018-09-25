package com.neo.repository;

import com.neo.entity.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
/**
 * 自定义的简单查询就是根据方法名来自动生成SQL
 * @author MeSweet
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Long deleteById(Long id);
    
    User findByUserName(String userName);
    
    User findByUserNameOrAge(String username, String age);

    Long countByUserName(String userName);
    
    List<User> findByUserNameLike(String userName);

    User findByUserNameIgnoreCase(String userName);
        
    List<User> findByUserNameOrderByAgeDesc(String userName);
    
    Page<User> findALL(Pageable pageable);
    
    Page<User> findByUserName(String userName,Pageable pageable);
    
    Page<User> queryFirst10ByUserName(String userName, Pageable pageable);
    
    User findFirstByOrderByAgeAsc();

    User findTopByOrderByAgeDesc();

    List<User> findFirst10ByUserName(String userName, Sort sort);

    List<User> findTop10ByUserName(String userName, Pageable pageable);
    
    @Modifying
    @Query("update User u set u.userName = ?1 where u.id = ?2")
    int modifyByIdAndUserId(String  userName, Long id);
    	
    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteByUserId(Long id);
      
    @Transactional(timeout = 10)
    @Query("select u from User u where u.emailAddress = ?1")
        User findByEmailAddress(String emailAddress);
    
    /*
     * 使用 @Query 的同时，用 @Modifying 来将该操作标识为修改查询
     * jpa底层实现会有二级缓存，也就是在更新完数据库后，如果后面去用这个对象，你再去查这个对象，这个对象是在一级缓存，但是并没有跟数据库同步，这个时候用clearAutomatically=true,就会刷新hibernate的一级缓存了
     */
    @Modifying(clearAutomatically=true)
    @Query("delete from User u where u.id = :id")
    void deleteUser(@Param("id")int id);
}