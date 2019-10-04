package com.example.PostawIWygraj.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.PostawIWygraj.model.User;

public interface UserService {
    List<User> findAll();

    void save(User user);

    void editUser(User user);

    User findById(Long id);

    User findByUsername(String username);

    List<UserDetails> getCurrentLoggedUsers();

    int getCountCurrentLoggedUsers();

}
