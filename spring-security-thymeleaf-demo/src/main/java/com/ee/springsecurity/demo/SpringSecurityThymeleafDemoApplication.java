package com.ee.springsecurity.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityThymeleafDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityThymeleafDemoApplication.class, args);
    }

    @Autowired
    private UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User adminUser = new User(null, "admin", passwordEncoder.encode("admin"), "ROLE_ADMIN");
        User normalUser = new User(null, "siva", passwordEncoder.encode("siva"), "ROLE_USER");
        repo.save(adminUser);
        repo.save(normalUser);
    }
}
