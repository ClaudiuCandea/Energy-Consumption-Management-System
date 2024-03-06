package com.example.userapp.DTOs;

import com.example.userapp.Entities.Role;

public class UserUpdateDTO {
    private String name;
    private Role role;
    private String password;
    private String email;

    public UserUpdateDTO(String name, Role role, String password, String email) {
        this.name = name;
        this.role = role;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
