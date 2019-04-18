package com.neo.model.repository.primary;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author neo
 * MongoRepository 会默认实现很多基本的增删改查
 */
public interface PrimaryRepository extends MongoRepository<PrimaryMongoObject, String> {
}
