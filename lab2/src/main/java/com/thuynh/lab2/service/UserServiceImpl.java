package com.thuynh.lab2.service;

import com.thuynh.lab2.entity.Post;
import com.thuynh.lab2.entity.User;
import com.thuynh.lab2.entity.dto.PostDto;
import com.thuynh.lab2.entity.dto.UserDto;
import com.thuynh.lab2.helper.ListMapper;
import com.thuynh.lab2.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}
