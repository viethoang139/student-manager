package com.leviethoang.security;

import com.leviethoang.service.impl.AccountServiceImpl;
import com.leviethoang.service.impl.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final UserDetailServiceImpl service;
    private final PasswordEncoder encoder;
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if(authentication.getCredentials() == null || userDetails.getPassword() == null){
            throw new BadCredentialsException("Credentials should not be null");
        }
        if(!encoder.matches((String)authentication.getCredentials(), userDetails.getPassword())){
            throw new BadCredentialsException("invalid credentials");

        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return service.loadUserByUsername(username);
    }
}
