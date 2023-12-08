package com.management.blogwebsite.service;

import com.management.blogwebsite.dto.RegistrationDto;
import com.management.blogwebsite.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
    User findUserByPost();
}
