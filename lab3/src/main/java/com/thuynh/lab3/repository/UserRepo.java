package com.thuynh.lab3.repository;

import com.thuynh.lab3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u where size(u.posts) > :n")
    List<User> findUsersWithMoreThanNPosts(@Param("n") int n);

    @Query("select u from User u where size(u.posts) > 1")
    List<User> findUsersWithMoreThanOnePost();

    @Query("select u from User u where size(u.posts) = 1")
    List<User> findUsersWithOnePost();

    List<User> findUsersByPostsTitleContainingIgnoreCase(String title);
}
