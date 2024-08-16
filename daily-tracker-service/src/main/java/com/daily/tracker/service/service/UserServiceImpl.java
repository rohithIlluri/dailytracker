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
            if (user != null && user.getUserId() != null) {
                String userId = user.getUserId().trim();  // Trim any extraneous spaces
                System.out.println("Attempting to delete user with ID: " + userId); // Log the user ID

                // Check if the user exists
                Optional<User> userOptional = userRepository.findById(userId);
                if (userOptional.isPresent()) {
                    userRepository.deleteById(userId);
                    return "User deleted successfully";
                } else {
                    return "User not found";
                }
            } else {
                return "Invalid user data";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete user";
        }
    }


    @Override
    public Optional<User> findById(String userId) {
        try {
            return userRepository.findById(String.valueOf(Long.valueOf(userId)));
        } catch (Exception e) {
            throw new RuntimeException("Error finding user", e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error finding users", e);
        }
    }

    @Override
    public String modify(User user) {
        try {
            Optional<User> existingUser = findById(user.getUserId());
            if (existingUser.isPresent()) {
                return save(user);
            } else {
                return "User not found";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error modifying user", e);
        }
    }

    @Override
    public List<User> searchUsers(String role, String status, String createdDate) {
        if (role != null && status != null && createdDate != null) {
            return userRepository.findByRoleAndStatusAndCreatedDate(role, status, createdDate);
        } else if (role != null && status != null) {
            return userRepository.findByRoleAndStatusAndCreatedDateLike(role, status, createdDate);
        } else {
            return List.of();
        }
    }
}
