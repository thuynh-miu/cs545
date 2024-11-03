package com.thuynh.lab4.service;

import com.thuynh.lab4.entity.dto.CommentDto;
import com.thuynh.lab4.entity.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();

    PostDto getById(long id);

    void save(PostDto p);

    void delete(long id);

    void update(long id, PostDto p);

    List<PostDto> searchPostsByTitle(String title);
    List<PostDto> searchPostsByAuthor(String author);

    CommentDto addComment(long postId, String commentText);
}
