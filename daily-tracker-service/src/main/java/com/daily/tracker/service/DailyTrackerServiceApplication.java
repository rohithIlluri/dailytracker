package com.daily.tracker.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.daily.tracker.service.repository")
public class DailyTrackerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyTrackerServiceApplication.class, args);
    }
}
