package com.thuynh.lab2.repository;

import com.thuynh.lab2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u where size(u.posts) > 1")
    List<User> findUsersWithMoreThanOnePost();
}
