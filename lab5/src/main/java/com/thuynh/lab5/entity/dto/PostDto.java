package com.thuynh.lab5.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostDto {
    long id;
    String title;
    String content;
    String author;

    List<CommentDto> comments;
}
