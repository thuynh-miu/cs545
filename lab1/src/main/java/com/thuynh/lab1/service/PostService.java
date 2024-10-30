package com.thuynh.lab1.service;

import com.thuynh.lab1.entity.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();

    PostDto getById(int id);

    void save(PostDto p);

    void delete(int id);

    void update(int id, PostDto p);

    List<PostDto> getPostsByAuthor(String author);

    List<PostDto> getPostsByAuthorContains(String text);
}
