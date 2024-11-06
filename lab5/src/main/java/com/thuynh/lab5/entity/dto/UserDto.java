package com.thuynh.lab5.entity.dto;

import com.thuynh.lab5.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    String name;
    List<Post> posts;
}
