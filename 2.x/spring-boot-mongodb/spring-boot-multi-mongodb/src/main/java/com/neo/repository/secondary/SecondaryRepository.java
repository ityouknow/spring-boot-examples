package com.neo.repository.secondary;

import com.neo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author neo
 */
public interface SecondaryRepository extends MongoRepository<User, String> {
}
