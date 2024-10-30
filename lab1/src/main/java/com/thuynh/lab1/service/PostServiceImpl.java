package com.thuynh.lab1.service;

import com.thuynh.lab1.entity.Post;
import com.thuynh.lab1.entity.dto.PostDto;
import com.thuynh.lab1.helper.ListMapper;
import com.thuynh.lab1.repository.PostRepo;
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
    public PostDto getById(int id) {
        return modelMapper.map(postRepo.getById(id), PostDto.class);
    }

    @Override
    public void save(PostDto p) {
        postRepo.save(modelMapper.map(p, Post.class));
    }

    @Override
    public void delete(int id) {
        postRepo.delete(id);
    }

    @Override
    public void update(int id, PostDto p) {
        postRepo.update(id, modelMapper.map(p, Post.class));
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
}
