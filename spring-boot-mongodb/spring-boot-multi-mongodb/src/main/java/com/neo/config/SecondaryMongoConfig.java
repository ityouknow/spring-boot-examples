package com.neo.config;

import com.neo.config.props.MultipleMongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.neo.repository.secondary",
		mongoTemplateRef = "secondaryMongoTemplate")
public class SecondaryMongoConfig {

}
