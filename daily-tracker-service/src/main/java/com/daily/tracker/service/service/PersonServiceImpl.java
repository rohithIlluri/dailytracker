package com.daily.tracker.service.service;

import com.daily.tracker.service.collection.Person;
import com.daily.tracker.service.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public String save(Person person) {
        return personRepository.save(person).getPersonId();
    }
}
