package com.example.userapp.Repository;

import com.example.userapp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByEmail(String email);

    User findUserById(Long id);

}
