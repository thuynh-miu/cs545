package com.thuynh.lab2.service;

import com.thuynh.lab2.entity.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();

    PostDto getById(long id);

    void save(PostDto p);

    void delete(long id);

    void update(long id, PostDto p);

    List<PostDto> getPostsByAuthor(String author);

    List<PostDto> getPostsByAuthorContains(String text);
}
