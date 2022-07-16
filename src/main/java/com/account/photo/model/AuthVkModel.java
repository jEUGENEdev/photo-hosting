package com.account.photo.model;

import com.account.photo.entity.User;
import com.account.photo.repository.UserRepository;
import com.account.photo.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthVkModel {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    public void auth(long uid, String firstName) {
        Optional<User> user = userRepository.findByVkId(uid);
        User newUser;
        if(user.isEmpty()) {
            newUser = new User(uid, firstName + (userRepository.count() + 1), null, "ROLE_USER", true);
            userRepository.save(newUser);
        }
        else
            newUser = user.get();
        auth(new UserDetails(newUser));
    }

    private void auth(UserDetails user) {
        SecurityContextHolder.getContext().setAuthentication(authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(user, user.getPassword())));
    }
}
