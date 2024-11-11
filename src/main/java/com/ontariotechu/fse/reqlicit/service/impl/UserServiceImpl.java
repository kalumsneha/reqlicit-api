package com.ontariotechu.fse.reqlicit.service.impl;

import com.ontariotechu.fse.reqlicit.exception.type.DuplicateException;
import com.ontariotechu.fse.reqlicit.exception.type.NotFoundException;
import com.ontariotechu.fse.reqlicit.exception.type.ServiceException;
import com.ontariotechu.fse.reqlicit.exception.type.UnauthorizedException;
import com.ontariotechu.fse.reqlicit.model.User;
import com.ontariotechu.fse.reqlicit.repository.UserRepository;
import com.ontariotechu.fse.reqlicit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return this.userRepository.save(user);
        }
        catch (ConstraintViolationException | DataIntegrityViolationException ex){
            log.error("Unable to create user. {}", ex.getMessage());
            throw new DuplicateException("Duplicate Entry");
        }
        catch (Exception ex){
            log.error("Unable to create user. {}", ex.getMessage());
            throw new ServiceException("Unable to create user");
        }
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.DESC, "username"));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(String username, User user) {
        User userToUpdate = this.userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Could not find user by the provided username"));
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        userToUpdate.setRole(user.getRole());
        return this.userRepository.save(userToUpdate);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return this.userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);

        Set<GrantedAuthority> authorities = null;
        if(user.isPresent()){
            authorities = Set.of(new SimpleGrantedAuthority("ROLE_".concat(user.get().getRole().name())));

            return new org.springframework.security.core.userdetails.User(
                    username,
                    user.get().getPassword(),
                    authorities
            );
        }

        throw new UnauthorizedException("User not found. Unauthorized!");


    }
}
