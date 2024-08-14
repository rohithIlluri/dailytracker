package com.daily.tracker.service.service;

import com.daily.tracker.service.collection.User;
import com.daily.tracker.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String save(User user) {
        try {
            userRepository.save(user);
            return "User saved successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public String delete(User user) {
        try {
            userRepository.delete(user);
            return "User deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    @Override
    public String modify(User user) {
        try {
            Optional<User>  existingUser = findById(user.getUserId());
            if(existingUser.isPresent()) {
                return save(user);
            }
        } catch (Exception e){
            throw new RuntimeException("Error modifying user", e);
        }
        return "Not Modified";
    }
    
    @Override
    public Optional<User> findById(String userId){
        try{
            return (userRepository.findById(Long.valueOf(userId)));
        } catch (Exception e) {
            throw new RuntimeException("Error finding user", e);
        }
    }
    @Override
    public List<User> findAll() {
        try{
            return (userRepository.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error finding user", e);
        }
    }
}

