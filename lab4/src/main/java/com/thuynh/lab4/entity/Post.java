package com.thuynh.lab4.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String content;
    String author;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    List<Comment> comments;

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
