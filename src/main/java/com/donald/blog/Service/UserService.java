package com.donald.blog.Service;

import com.donald.blog.Models.User;
import com.donald.blog.Models.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    User save(UserRegistrationDto user);
}
