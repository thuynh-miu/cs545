package com.thuynh.lab2.controller;

import com.thuynh.lab2.entity.Post;
import com.thuynh.lab2.entity.dto.PostDto;
import com.thuynh.lab2.entity.dto.UserDto;
import com.thuynh.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDto> getAll(@RequestParam(required = false) Boolean multiPost) {
        if (multiPost != null) {
            if (multiPost) {
                return userService.getUsersWithMoreThanOnePost();
            }
            else {
                return userService.getUsersWithOnePost();
            }
        } else {
            return userService.findAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable int id) {
        var user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long userId) {
        List<PostDto> posts = userService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody UserDto u) {
        userService.save(u);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int postId, @RequestBody UserDto u) {
        userService.update(postId, u);
    }
}
