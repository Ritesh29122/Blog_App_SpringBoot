package com.example.BLOG_APP.controller;


import com.example.BLOG_APP.payloads.ApiResponse;
import com.example.BLOG_APP.payloads.UserDto;
import com.example.BLOG_APP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    //POST  create user
    @PostMapping("/")
    public ResponseEntity<UserDto>createUser(@RequestBody UserDto userdto){
        UserDto Createuserdto=this.userService.createUser(userdto);
        return new ResponseEntity<>(Createuserdto, HttpStatus.CREATED);
    }

    //PUT update user
    @PutMapping("/{user_id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto, @PathVariable("user_id") Integer user_id){
        UserDto Updateuserdto=this.userService.updateUser(userdto,user_id);
        return new ResponseEntity<>(Updateuserdto,HttpStatus.OK);

    }
    //DELETE user
    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse>DeleteUser(@RequestBody UserDto userdto, @PathVariable("user_id") Integer user_id){
        this.userService.deleteUser(user_id);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

    //GET user
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> GetUserById(@RequestBody UserDto userdto,@PathVariable("user_id") Integer user_id){
        UserDto Getuserdto=this.userService.getUserBYId(userdto,user_id);
        return new ResponseEntity<>(Getuserdto,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> GetAllUsers(){
        List<UserDto>AllUsers=this.userService.getAllUsers();
        return new ResponseEntity<>(AllUsers,HttpStatus.OK);
    }
}
