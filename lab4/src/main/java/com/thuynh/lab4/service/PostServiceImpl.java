package com.thuynh.lab4.service;

import com.thuynh.lab4.entity.Comment;
import com.thuynh.lab4.entity.Post;
import com.thuynh.lab4.entity.dto.CommentDto;
import com.thuynh.lab4.entity.dto.PostDto;
import com.thuynh.lab4.helper.ListMapper;
import com.thuynh.lab4.repository.PostRepo;
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

    @Override
    public List<PostDto> searchPostsByTitle(String title) {
        return listMapper.mapList(postRepo.findByTitleContainingIgnoreCase(title), PostDto.class);
    }

    @Override
    public List<PostDto> searchPostsByAuthor(String author) {
        return listMapper.mapList(postRepo.findByAuthorContainingIgnoreCase(author), PostDto.class);
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
