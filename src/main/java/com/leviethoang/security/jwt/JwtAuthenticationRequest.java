package com.leviethoang.security.jwt;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private String username;
    private String password;
}
