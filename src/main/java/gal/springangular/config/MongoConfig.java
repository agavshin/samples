package gal.springangular.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("gal.springangular.repository")
public class MongoConfig {
}
