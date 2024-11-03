package com.thuynh.lab4.repository;

import com.thuynh.lab4.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
