package com.thuynh.lab1.repository;

import com.thuynh.lab1.entity.Post;

import java.util.List;

public interface PostRepo {
    List<Post> findAll();

    Post getById(int id);

    void save(Post p);

    void delete(int id);

    void update(int id, Post p);
}
