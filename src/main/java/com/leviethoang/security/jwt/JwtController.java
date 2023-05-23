package com.leviethoang.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
public class JwtController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String getTokenForAuthenticatedUser(@RequestBody
                                               JwtAuthenticationRequest jwtAuthenticationRequest
    ){
        Authentication authentication =
                authenticationManager
                        .authenticate
                                (new UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getUsername(),jwtAuthenticationRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.getGeneratedToken(jwtAuthenticationRequest.getUsername());
        }
        else{
            throw new UsernameNotFoundException("Invalid user credentials");
        }
    }
}
