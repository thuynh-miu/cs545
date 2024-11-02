package com.thuynh.lab3.entity.dto;

import com.thuynh.lab3.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    String name;
    List<Post> posts;
}
