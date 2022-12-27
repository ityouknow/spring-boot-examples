package com.neo.config;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.neo.config.props.MultipleMongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


@Configuration
public class MultipleMongoConfig {

	@Autowired
    private MultipleMongoProperties mongoProperties;

	@Primary
	@Bean(name = "primaryMongoTemplate")
	public MongoTemplate primaryMongoTemplate() throws Exception {
		return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
	}

	@Bean
	@Qualifier("secondaryMongoTemplate")
	public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
	}

	@Bean
    @Primary
	public MongoDatabaseFactory primaryFactory(MongoProperties mongo) throws Exception {
		MongoClient client = MongoClients.create(mongo.getUri());
		return new SimpleMongoClientDatabaseFactory(client, mongoProperties.getPrimary().getDatabase());
	}

	@Bean
	public MongoDatabaseFactory secondaryFactory(MongoProperties mongo) throws Exception {
		MongoClient client = MongoClients.create(mongo.getUri());
		return new SimpleMongoClientDatabaseFactory(client, mongoProperties.getSecondary().getDatabase());
	}
}