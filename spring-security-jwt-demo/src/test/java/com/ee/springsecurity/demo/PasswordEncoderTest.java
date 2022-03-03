package com.ee.springsecurity.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPwd = encoder.encode("admin");
        final boolean matching = encoder.matches("admin", encryptedPwd);
        System.out.println("matching = " + matching);
    }
}
