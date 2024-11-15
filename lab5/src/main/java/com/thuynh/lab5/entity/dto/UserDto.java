package com.thuynh.lab5.entity.dto;

import com.thuynh.lab5.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    long id;
    String name;
    List<Post> posts;
}
