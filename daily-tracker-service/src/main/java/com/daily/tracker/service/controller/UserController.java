package com.daily.tracker.service.controller;

import com.daily.tracker.service.collection.User;

import com.daily.tracker.service.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tracker")
public class UserController {

    @Qualifier("userServiceImpl")
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add-user")
    public ResponseEntity<String> save(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUsers (@RequestParam(required = false) String userId) {

        try {
            if (userId != null) {
            return new ResponseEntity<>(userService.findById((userId)).orElse(null),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.CREATED);
        }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search-users")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String createdDate
    )
    {
        try {
            List<User> users = userService.searchUsers(role, status, createdDate);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Return error status and message
        }

    }

    @PutMapping("/modify-user")
    public ResponseEntity<String> modifyUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.modify(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to modify user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody(required = false) User user) {
        if (user == null || user.getUserId() == null) {
            return ResponseEntity.badRequest().body("User ID is missing in the request body");
        }

        try {
            String result = userService.delete(user);
            if ("User deleted successfully".equals(result)) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

