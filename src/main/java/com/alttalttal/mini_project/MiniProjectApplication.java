package com.alttalttal.mini_project;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
// mysql repository와 mongodb repository의 package로 나눔
@EnableJpaRepositories(basePackages = "com.alttalttal.mini_project.repository")
@EnableMongoRepositories(basePackages = "com.alttalttal.mini_project.mongo.repository")
public class MiniProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniProjectApplication.class, args);
    }

}
