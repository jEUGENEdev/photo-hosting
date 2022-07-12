package com.account.photo.model;

import com.account.photo.entity.User;
import com.account.photo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserModel {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String create(String vkId, String username, String role, String creatorPassword) {
        try {
            long vkID = Long.parseLong(vkId);
            byte userRole = Byte.parseByte(role);
            if(userRepository.findByPassword(creatorPassword).isEmpty() ||
                    userRepository.findByUsernameOrVkId(username, vkID) != null || creatorPassword == null)
                throw new RuntimeException();
            else {
                switch(userRole) {
                    case 0 -> role = "ROLE_USER";
                    case 1 -> role = "ROLE_MODER";
                    case 2 -> role = "ROLE_ADMIN";
                    default -> throw new RuntimeException();
                }
                String password = passwordGenerator();
                userRepository.save(new User(vkID, username, passwordEncoder.encode(password), role, true));
                return String.format("{\"status\": \"ok\", \"username\": \"%s\", \"password\": \"%s\"}", username, password);
            }
        } catch (RuntimeException e) {
            return "{\"status\": \"err\"}";
        }
    }

    private String passwordGenerator() {
        Random random = new Random();
        StringBuilder pass = new StringBuilder();
        for(int i=0; i<10; i++)
            pass.append((char) (random.nextInt(43) + 48));
        return pass.toString();
    }
}
