package com.ee.springsecurity.demo.web;

import com.ee.springsecurity.demo.domain.User;
import com.ee.springsecurity.demo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public Map<String,String> index() {
        return Map.of("status", "up");
    }

    @GetMapping("/user")
    //@PreAuthorize("hasRole('ROLE_USER')")
    @PreAuthorize("isAuthenticated()")
    public User user(@AuthenticationPrincipal String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
