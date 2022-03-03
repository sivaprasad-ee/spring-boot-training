package com.ee.springsecurity.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class LoginResponse {
    private String accessToken;
    private Date expiresAt;
}
