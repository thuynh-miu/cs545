package com.thuynh.lab4.service;

import com.thuynh.lab4.entity.dto.CommentDto;
import com.thuynh.lab4.entity.dto.PostDto;
import com.thuynh.lab4.entity.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto getById(long id);

    void save(UserDto p);

    void delete(long id);

    void update(long id, UserDto p);

    List<PostDto> getPostsByUserId(Long userId);

    List<UserDto> getUsersWithMoreThanOnePost();
    List<UserDto> getUsersWithOnePost();
    List<UserDto> getUsersWithMoreThanNPosts(int n);

    List<UserDto> getUsersByPosts(String title);

    PostDto getPostByUserIdPostId(long userId, long postId);
    List<CommentDto> getCommentsByUserIdPostId(long userId, long postId);
    CommentDto getCommentByUserIdPostIdCommendId(long userId, long postId, long commentID);
}
