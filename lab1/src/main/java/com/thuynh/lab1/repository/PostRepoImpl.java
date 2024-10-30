package com.thuynh.lab1.repository;

import com.thuynh.lab1.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepoImpl implements PostRepo {
    private static List<Post> posts;
    private static int productId = 300;
    static {
        posts = new ArrayList<>();

        Post post1 = new Post();
        post1.setTitle("Introduction to Spring Boot");
        post1.setContent("Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");
        post1.setAuthor("Alice");

        Post post2 = new Post();
        post2.setTitle("Understanding Dependency Injection");
        post2.setContent("Dependency Injection is a design pattern used to implement IoC, in which the control of objects is transferred to a container.");
        post2.setAuthor("Bob");

        Post post3 = new Post();
        post3.setTitle("RESTful APIs with Spring Boot");
        post3.setContent("Creating RESTful APIs in Spring Boot is straightforward and can be achieved using @RestController annotations.");
        post3.setAuthor("Charlie");

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Post getById(int id) {
        return posts
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Post p) {
        p.setId(productId); // We are auto generating the id for DEMO purposes, (Normally, do not change your parameters)
        productId++;
        posts.add(p);
    }

    @Override
    public void delete(int id) {
        var product = posts
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst().get();
        posts.remove(product);
    }

    @Override
    public void update(int id, Post p) {
        Post toUpdate = getById(id);
        toUpdate.setTitle(p.getTitle());
        toUpdate.setContent(p.getContent());
        toUpdate.setAuthor(p.getAuthor());
    }
}
