package com.neo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);
    
}