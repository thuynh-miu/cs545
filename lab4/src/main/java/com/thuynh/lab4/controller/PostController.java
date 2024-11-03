package com.thuynh.lab4.controller;

import com.thuynh.lab4.entity.dto.CommentDto;
import com.thuynh.lab4.entity.dto.PostDto;
import com.thuynh.lab4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/posts")
@RestController
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public List<PostDto> getAll() {
        return postService.findAll();
    }

    // Filter by partial text in author name
    @GetMapping("/filter")
    public List<PostDto> searchPosts(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title) {
        if (author != null) {
            return postService.searchPostsByAuthor(author);
        }
        else if (title != null) {
            return postService.searchPostsByTitle(title);
        }
        else {
            return postService.findAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable int id) {
        var post = postService.getById(id);
        return ResponseEntity.ok(post);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody PostDto p) {
        postService.save(p);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int postId, @RequestBody PostDto p) {
        postService.update(postId, p);
    }

    @PostMapping("/{postId}/comment")
    public CommentDto addComment(@PathVariable int postId, @RequestBody CommentDto comment) {
        return postService.addComment(postId, comment.getName());
    }
}
