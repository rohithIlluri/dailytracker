package com.daily.tracker.service.controller;

import com.daily.tracker.service.collection.User;
import com.daily.tracker.service.service.UserService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PutMapping("/modify-user")
    public ResponseEntity<String> modifyUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.modify(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to modify user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>( userService.delete(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
