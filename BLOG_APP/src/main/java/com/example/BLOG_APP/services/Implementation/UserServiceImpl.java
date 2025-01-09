package com.example.BLOG_APP.services.Implementation;

import com.example.BLOG_APP.Exceptions.ResourceNotFoundException;
import com.example.BLOG_APP.models.User;
import com.example.BLOG_APP.payloads.UserDto;
import com.example.BLOG_APP.repositories.UserRepo;
import com.example.BLOG_APP.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDto createUser(UserDto userdto) {
        User user=this.UserDtoToUser(userdto);
        User savedUser=this.userRepo.save(user);
        return this.UserToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userdto, Integer userId) {
        User user=this.UserDtoToUser(userdto);
        User ExistingUser=userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        ExistingUser.setName(userdto.getName());
        ExistingUser.setId(userdto.getId());
        ExistingUser.setEmail(userdto.getEmail());
        ExistingUser.setPassword(userdto.getPassword());
        ExistingUser.setAbout(userdto.getAbout());
        User updateUser=userRepo.save(ExistingUser);
        return this.UserToUserDto(updateUser);
    }

    @Override
    public UserDto getUserBYId(UserDto user, Integer userId) {
        User ExistingUser=userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        return this.UserToUserDto(ExistingUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> ExistingUser=this.userRepo.findAll();
        List<UserDto> userdtolist=ExistingUser.stream().map(user->this.UserToUserDto(user)).collect(Collectors.toList());
        return userdtolist;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

    private User UserDtoToUser(UserDto userdto){
            User user= this.modelMapper.map(userdto,User.class);
//            user.setName(userdto.getName());
//            user.setId(userdto.getId());
//            user.setEmail((userdto.getEmail()));
//            user.setPassword(userdto.getPassword());
//            user.setAbout(userdto.getAbout());
            return user;
    }

    private UserDto UserToUserDto(User user){
        UserDto userdto=this.modelMapper.map(user,UserDto.class);
//        userdto.setEmail(user.getEmail());
//        userdto.setPassword(user.getPassword());
//        userdto.setName(user.getName());
//        userdto.setId(user.getId());
//        userdto.setAbout(user.getAbout());
        return userdto;
    }
}
