package com.daily.tracker.service.repository;

import com.daily.tracker.service.collection.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends MongoRepository<Person, String> {


}

