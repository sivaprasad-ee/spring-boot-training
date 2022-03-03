package com.ee.springsecurity.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class SpringSecurityThymeleafDemoApplicationTests {

    @Test
    @WithMockUser()
    void contextLoads() {
    }

}
