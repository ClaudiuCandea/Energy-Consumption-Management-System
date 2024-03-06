package com.example.userapp.Configuration;

import com.example.userapp.Entities.Role;
import com.example.userapp.Entities.User;
import com.example.userapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class StartRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    StartRunner(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("aici");
        if(userRepository.findAll().isEmpty()){
            User user = new User("Claudiu","claudiu@gmail.com",passwordEncoder.encode("123"), Role.ADMIN);
            userRepository.save(user);
        }
        else{
            System.out.println("Database not empty");
        }
    }
}
