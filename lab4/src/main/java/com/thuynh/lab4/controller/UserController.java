package com.thuynh.lab4.controller;

import com.thuynh.lab4.aspect.annotation.ExecutionTime;
import com.thuynh.lab4.entity.Post;
import com.thuynh.lab4.entity.dto.CommentDto;
import com.thuynh.lab4.entity.dto.PostDto;
import com.thuynh.lab4.entity.dto.UserDto;
import com.thuynh.lab4.service.UserService;
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

    @GetMapping("/n-posts")
    public List<UserDto> getAll(@RequestParam() Integer n) {
        if (n == null || n < 1) {
            throw new IllegalArgumentException("Parameter 'n' must be a positive integer.");
        }

        return userService.getUsersWithMoreThanNPosts(n);
    }

    @GetMapping("/{id}")
    @ExecutionTime
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

    @GetMapping("/filter/posts")
    public List<UserDto> searchUsersByPosts(@RequestParam(required = false) String title) {
        return userService.getUsersByPosts(title);
    }

    @GetMapping("/{userId}/posts/{postId}")
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
