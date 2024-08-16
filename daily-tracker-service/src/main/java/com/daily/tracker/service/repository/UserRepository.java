package com.daily.tracker.service.repository;

import com.daily.tracker.service.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Find users by role, status, and exact createdDate
    @Query("{ 'role': ?0, 'status': ?1, 'createdDate': ?2 }")
    List<User> findByRoleAndStatusAndCreatedDate(String role, String status, String createdDate);

    // Find users by role, status, and similar createdDate (e.g., by day)
    List<User> findByRoleAndStatusAndCreatedDateLike(String role, String status, String createdDate);
}
