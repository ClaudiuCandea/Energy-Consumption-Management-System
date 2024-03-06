package com.example.userapp.Controllers;

import com.example.userapp.DTOs.UserDTO;
import com.example.userapp.DTOs.UserLoginDTO;
import com.example.userapp.DTOs.UserUpdateDTO;
import com.example.userapp.DTOs.UserViewDTO;
import com.example.userapp.Entities.AuthenticationResponse;
import com.example.userapp.Entities.User;
import com.example.userapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( path = "/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserViewDTO>>getUsers(){
        return ResponseEntity.ok(this.userService.findAllUsers());
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserViewDTO> getUserById(@RequestParam(value = "id") Long id){
        return ResponseEntity.ok(this.userService.findUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(this.userService.addUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserLoginDTO userLoginDTO){
        return ResponseEntity.ok(this.userService.login(userLoginDTO));
    }

    @PutMapping("/update")
    public UserViewDTO updateUser(@RequestBody UserUpdateDTO user, @RequestParam(value = "id") Long id){
        return this.userService.updateUser(user, id);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteUser(@RequestParam(value = "id") Long id){
         this.userService.deleteUserById(id);
         return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
