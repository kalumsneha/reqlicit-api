package com.ontariotechu.fse.reqlicit.config;

import com.ontariotechu.fse.reqlicit.model.User;
import com.ontariotechu.fse.reqlicit.model.enums.Role;
import com.ontariotechu.fse.reqlicit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Load initial data into the database
        if(userService.getUserByUsername("mfernando").isEmpty())
            userService.createUser(new User("mfernando", "mfernando@gmail.com", "password1", Role.ADMIN));
    }
}