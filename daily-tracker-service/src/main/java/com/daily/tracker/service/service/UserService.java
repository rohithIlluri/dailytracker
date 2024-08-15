package com.daily.tracker.service.service;

import com.daily.tracker.service.collection.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String save(User user); // Create or Update user
    String delete(User user); // Delete a user
    Optional<User> findById(String id); // Find user by ID
    List<User> findAll();
    String modify(User user);

}
