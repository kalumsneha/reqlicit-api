package com.ontariotechu.fse.reqlicit.service;

import com.ontariotechu.fse.reqlicit.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User createUser(User user);

    List<User> getUsers();

    Optional<User> getUserByUsername(String username);

    User updateUser(String username, User user);

    Optional<User> getUserById(Integer id);
}
