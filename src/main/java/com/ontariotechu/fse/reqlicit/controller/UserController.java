package com.ontariotechu.fse.reqlicit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.model.User;
import com.ontariotechu.fse.reqlicit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public User createUser(@RequestBody User user) throws JsonProcessingException {
        log.info("creating user. User: {}", new ObjectMapper().writeValueAsString(user));
        return this.userService.createUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<User> getUsers() throws JsonProcessingException {
        log.info("fetching users..");
        return this.userService.getUsers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{username}")
    public Optional<User> getUserByUserId(@PathVariable String username) throws JsonProcessingException {
        log.info("fetching user by username. username: {}", username);
        return Optional.ofNullable(this.userService.getUserByUsername(username).orElseThrow(() -> new NotFoundException("Could not find user by the provided username")));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) throws JsonProcessingException {
        log.info("updating user. username: {}, user: {}", username, new ObjectMapper().writeValueAsString(user));
        return this.userService.updateUser(username, user);
    }
}
