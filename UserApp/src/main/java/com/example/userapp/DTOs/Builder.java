package com.example.userapp.DTOs;

import com.example.userapp.Entities.Role;
import com.example.userapp.Entities.User;

public class Builder {

    public static User toUser(UserDTO userDTO){
        return new User(userDTO.getName(),userDTO.getEmail(),userDTO.getPassword(), Role.CLIENT);
    }
    public static UserDTO toUserDTO(User user){
        return new UserDTO(user.getName(),user.getPassword(),user.getUsername());
    }

    public static UserViewDTO toUserViewDTO(User user){
        return new UserViewDTO(user.getId(),user.getName(),user.getUsername(),user.getPassword(), user.getRole());
    }
}
