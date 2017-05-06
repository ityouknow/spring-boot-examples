package com.neo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author neo
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.neo.model.repository.secondary",
		mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
public class SecondaryMongoConfig {

	protected static final String MONGO_TEMPLATE = "secondaryMongoTemplate";
}
