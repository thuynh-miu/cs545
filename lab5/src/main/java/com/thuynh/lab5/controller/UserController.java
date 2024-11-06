package com.thuynh.lab5.controller;

import com.thuynh.lab5.aspect.annotation.ExecutionTime;
import com.thuynh.lab5.aspect.annotation.LogMe;
import com.thuynh.lab5.entity.Post;
import com.thuynh.lab5.entity.dto.CommentDto;
import com.thuynh.lab5.entity.dto.PostDto;
import com.thuynh.lab5.entity.dto.UserDto;
import com.thuynh.lab5.service.UserService;
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

    @LogMe
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

    @GetMapping("/n-posts")
    @LogMe
    public List<UserDto> getAll(@RequestParam() Integer n) {
        if (n == null || n < 1) {
            throw new IllegalArgumentException("Parameter 'n' must be a positive integer.");
        }

        return userService.getUsersWithMoreThanNPosts(n);
    }

    @GetMapping("/{id}")
    @ExecutionTime
    @LogMe
    public ResponseEntity<UserDto> getById(@PathVariable int id) {
        var user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/posts")
    @LogMe
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long userId) {
        List<PostDto> posts = userService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @LogMe
    public void save(@RequestBody UserDto u) {
        userService.save(u);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @LogMe
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int postId, @RequestBody UserDto u) {
        userService.update(postId, u);
    }

    @GetMapping("/filter/posts")
    public List<UserDto> searchUsersByPosts(@RequestParam(required = false) String title) {
        return userService.getUsersByPosts(title);
    }

    @GetMapping("/{userId}/posts/{postId}")
    @LogMe
    public ResponseEntity<PostDto> getPostByUserIdPostId(@PathVariable Long userId,
                                                         @PathVariable Long postId) {
        PostDto post = userService.getPostByUserIdPostId(userId, postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/posts/{postId}/comments")
    @LogMe
    public ResponseEntity<List<CommentDto>> getCommentsByUserIdPostId(@PathVariable Long userId,
                                                                      @PathVariable Long postId) {
        List<CommentDto> comments = userService.getCommentsByUserIdPostId(userId, postId);
        if (comments != null) {
            return ResponseEntity.ok(comments);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/posts/{postId}/comments/{commentId}")
    @LogMe
    public ResponseEntity<CommentDto> getCommentsByUserIdPostIdCommendId(@PathVariable Long userId,
                                                                     @PathVariable Long postId,
                                                                     @PathVariable Long commentId) {
        CommentDto comment = userService.getCommentByUserIdPostIdCommendId(userId, postId, commentId);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
