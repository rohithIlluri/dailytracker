package com.daily.tracker.service.repository;

import com.daily.tracker.service.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

}

