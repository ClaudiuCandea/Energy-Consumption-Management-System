package com.example.userapp.Services;

import com.example.userapp.DTOs.*;
import com.example.userapp.Entities.AuthenticationResponse;
import com.example.userapp.Entities.Role;
import com.example.userapp.Entities.User;
import com.example.userapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public List<UserViewDTO> findAllUsers(){
       List<User> users = this.userRepository.findAll();
       List<UserViewDTO> userViewDTOS = new ArrayList<>();
       for(User user: users){
           userViewDTOS.add(Builder.toUserViewDTO(user));
       }
       return userViewDTOS;
    }


    public UserViewDTO findUserById(Long id){
        return Builder.toUserViewDTO( this.userRepository.findUserById(id));
    }

    public AuthenticationResponse addUser(UserDTO userDTO){
        User user = Builder.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.CLIENT);
        this.userRepository.save(user);
        Map<String,Object> claims = new HashMap<>();
        claims.put("role",Role.CLIENT);
        String jwtToken = jwtService.generateToken(claims,user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse login(UserLoginDTO userLoginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getEmail(),
                        userLoginDTO.getPassword()
                )
        );
        User user = userRepository.findUserByEmail(userLoginDTO.getEmail());
        if(user== null){
            return null;
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put("role",user.getRole());
        claims.put("id",user.getId());
        String jwtToken = jwtService.generateToken(claims,user);
        return new AuthenticationResponse(jwtToken);

    }

    public UserViewDTO updateUser(UserUpdateDTO userDTO, Long id){
        User oldUser = this.userRepository.findUserById(id);
        oldUser.setName(userDTO.getName());
        oldUser.setRole(userDTO.getRole());
        oldUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        oldUser.setEmail(userDTO.getEmail());
        User newUser =  this.userRepository.save(oldUser);
        return Builder.toUserViewDTO(newUser);
    }

    public void deleteUserById(Long id){
         this.userRepository.deleteById(id);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByEmail(username);
    }
}
