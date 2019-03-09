package com.neo.repository;

import com.neo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);

    @Transactional(timeout = 10)
    @Modifying
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String  userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteById(Long id);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query("select u from User u")
    Page<User> findALL(Pageable pageable);

    Page<User> findByNickName(String nickName, Pageable pageable);

    Slice<User> findByNickNameAndEmail(String nickName, String email,Pageable pageable);


}