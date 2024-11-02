package com.thuynh.lab3.service;

import com.thuynh.lab3.entity.dto.PostDto;
import com.thuynh.lab3.entity.dto.UserDto;

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
}
