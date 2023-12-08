package com.management.blogwebsite.service.impl;

import com.management.blogwebsite.dto.RegistrationDto;
import com.management.blogwebsite.entity.Post;
import com.management.blogwebsite.entity.Role;
import com.management.blogwebsite.entity.User;
import com.management.blogwebsite.repository.PostRepository;
import com.management.blogwebsite.repository.RoleRepository;
import com.management.blogwebsite.repository.UserRepository;
import com.management.blogwebsite.service.UserService;
import com.management.blogwebsite.util.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private PostRepository postRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        // use spring security to encrypt the password
         user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByPost() {
        return null;
    }
}
