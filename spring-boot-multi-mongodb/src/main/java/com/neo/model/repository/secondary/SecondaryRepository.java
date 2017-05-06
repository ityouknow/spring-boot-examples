package com.neo.model.repository.secondary;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author neo
 */
public interface SecondaryRepository extends MongoRepository<SecondaryMongoObject, String> {
}
