package com.account.photo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = (UserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if(user.getPassword() == null || user.getPassword().equals(passwordEncoder.encode(authentication.getCredentials().toString())))
            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        throw new BadCredentialsException("Error password!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
