package com.thuynh.lab3.repository;

import com.thuynh.lab3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findByAuthorContainingIgnoreCase(String author);
    List<Post> findByTitleContainingIgnoreCase(String title);
}
