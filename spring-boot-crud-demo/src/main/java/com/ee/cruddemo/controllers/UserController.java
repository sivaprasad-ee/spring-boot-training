package com.ee.cruddemo.controllers;

import com.ee.cruddemo.UserNotFoundException;
import com.ee.cruddemo.entities.User;
import com.ee.cruddemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/by-email")
    public User getByEmail(@RequestParam String email) {
        //userRepository.findByUsernameAndPassword("siva", "prasad");
        //Sort sort = Sort.by(Sort.Order.asc("username"), Sort.Order.desc("lastName"));
        final User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UserNotFoundException("User with email="+email+" not found");
        }
        return user;
    }
}
