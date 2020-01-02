package com.codewindy.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig {
	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {

		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));// 去除_class column

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory,
				converter);

		return mongoTemplate;

	}

	/*
	 * @Configuration public class MongoConfig {
	 * 
	 * @Autowired MongoDbFactory mongoDbFactory;
	 * 
	 * @Autowired MongoMappingContext mongoMappingContext;
	 * 
	 * @Bean public MappingMongoConverter mappingMongoConverter() {
	 * 
	 * DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
	 * MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver,
	 * mongoMappingContext); converter.setTypeMapper(new
	 * DefaultMongoTypeMapper(null));
	 * 
	 * return converter; } }
	 */
}
