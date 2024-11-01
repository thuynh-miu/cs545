package com.thuynh.lab2.repository;

import com.thuynh.lab2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
