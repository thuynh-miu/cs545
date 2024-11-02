package com.thuynh.lab3.service;

import com.thuynh.lab3.entity.Comment;
import com.thuynh.lab3.entity.Post;
import com.thuynh.lab3.entity.dto.CommentDto;
import com.thuynh.lab3.entity.dto.PostDto;
import com.thuynh.lab3.helper.ListMapper;
import com.thuynh.lab3.repository.PostRepo;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @Override
    public List<PostDto> findAll() {
        return listMapper.mapList(postRepo.findAll(), PostDto.class);
    }

    @Override
    public PostDto getById(long id) {
        var post = postRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void save(PostDto p) {
        postRepo.save(modelMapper.map(p, Post.class));
    }

    @Override
    public void delete(long id) {
        postRepo.deleteById(id);
    }

    @Override
    public void update(long id, PostDto p) {
        if (postRepo.existsById(id)) {  // Check if the entity exists
            Post post = modelMapper.map(p, Post.class);
            post.setId(id);  // Set the ID explicitly to ensure it updates the correct entity
            postRepo.save(post);  // Save will now update the existing record
        } else {
            throw new EntityNotFoundException("Post not found with id " + id);
        }
    }

    // Filter posts by exact author name
    public List<PostDto> getPostsByAuthor(String author) {
        return postRepo.findAll().stream()
                .filter(post -> post.getAuthor().equalsIgnoreCase(author))
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    // Filter posts by partial author name
    public List<PostDto> getPostsByAuthorContains(String text) {
        return postRepo.findAll().stream()
                .filter(post -> post.getAuthor().toLowerCase().contains(text.toLowerCase()))
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    public CommentDto addComment(long postId, String commentText) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found with id " + postId));
        Comment comment = new Comment();
        comment.setName(commentText);
        post.addComment(comment);
        postRepo.save(post);
        return modelMapper.map(comment, CommentDto.class);
    }
}
