package com.neo.mapper.test2;

import java.util.List;

import com.neo.entity.UserEntity;

public interface User2Mapper {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}