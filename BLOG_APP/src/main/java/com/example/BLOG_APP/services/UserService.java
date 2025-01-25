package com.example.BLOG_APP.services;

import com.example.BLOG_APP.models.User;
import com.example.BLOG_APP.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    List<UserDto>getAllUsers();
    void deleteUser(Integer userId);

    UserDto getUserById(Integer userId);
}
