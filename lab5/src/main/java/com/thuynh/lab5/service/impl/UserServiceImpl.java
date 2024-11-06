package com.thuynh.lab5.service.impl;

import com.thuynh.lab5.entity.Comment;
import com.thuynh.lab5.entity.Post;
import com.thuynh.lab5.entity.User;
import com.thuynh.lab5.entity.dto.CommentDto;
import com.thuynh.lab5.entity.dto.PostDto;
import com.thuynh.lab5.entity.dto.UserDto;
import com.thuynh.lab5.helper.ListMapper;
import com.thuynh.lab5.repository.UserRepo;
import com.thuynh.lab5.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @Override
    public List<UserDto> findAll() {
        return listMapper.mapList(userRepo.findAll(), UserDto.class);
    }

    @Override
    public UserDto getById(long id) {
        var user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void save(UserDto u) {
        userRepo.save(modelMapper.map(u, User.class));
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void update(long id, UserDto u) {
        if (userRepo.existsById(id)) {
            User user = modelMapper.map(u, User.class);
            user.setId(id);
            userRepo.save(user);
        } else {
            throw new EntityNotFoundException("User not found with id " + id);
        }
    }

    @Override
    public List<PostDto> getPostsByUserId(Long userId) {
        return listMapper.mapList(getById(userId).getPosts(), PostDto.class);
    }

    @Override
    public List<UserDto> getUsersWithMoreThanOnePost() {
        return listMapper.mapList(userRepo.findUsersWithMoreThanOnePost(), UserDto.class);
    }

    @Override
    public List<UserDto> getUsersWithOnePost() {
        return listMapper.mapList(userRepo.findUsersWithOnePost(), UserDto.class);
    }

    @Override
    public List<UserDto> getUsersWithMoreThanNPosts(int n) {
        return listMapper.mapList(userRepo.findUsersWithMoreThanNPosts(n), UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByPosts(String title) {
        return listMapper.mapList(userRepo.findUsersByPostsTitleContainingIgnoreCase(title), UserDto.class);
    }

    @Override
    public PostDto getPostByUserIdPostId(long userId, long postId) {
        AtomicReference<Post> foundPost = new AtomicReference<>();

        userRepo.findById(userId).ifPresent(user -> {
            user.getPosts().stream()
                    .filter(post -> post.getId() == postId)
                    .findFirst()
                    .ifPresent(foundPost::set);
        });

        return foundPost.get() != null ? modelMapper.map(foundPost.get(), PostDto.class)
                : null;
    }

    @Override
    public List<CommentDto> getCommentsByUserIdPostId(long userId, long postId) {
        AtomicReference<List<CommentDto>> foundComments = new AtomicReference<>();

        userRepo.findById(userId).ifPresent(user -> {
            user.getPosts().stream()
                    .filter(post -> post.getId() == postId)
                    .findFirst()
                    .ifPresent(post -> {
                        foundComments.set(listMapper.mapList(post.getComments(), CommentDto.class));
                    });
        });

        return foundComments.get() != null ? foundComments.get() : null;
    }

    @Override
    public CommentDto getCommentByUserIdPostIdCommendId(long userId, long postId, long commentId) {
        AtomicReference<Comment> foundComment = new AtomicReference<>();

        userRepo.findById(userId).ifPresent(user -> {
            user.getPosts().stream()
                    .filter(post -> post.getId() == postId)
                    .findFirst()
                    .ifPresent(post -> {
                        post.getComments().stream()
                                .filter(comment -> comment.getId() == commentId)
                                .findFirst()
                                .ifPresent(foundComment::set);
                    });
        });

        return foundComment.get() != null ? modelMapper.map(foundComment.get(), CommentDto.class)
                : null;
    }
}
