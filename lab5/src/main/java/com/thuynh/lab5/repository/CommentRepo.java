package com.thuynh.lab5.repository;

import com.thuynh.lab5.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
