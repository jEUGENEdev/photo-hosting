package com.account.photo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${upload.photo.url}")
    private String uploadPhotoUrl;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers("/", "/static/**", String.format("%s/**", uploadPhotoUrl), "/login", "/auth",
                        "/archive", "/photos/*", "/users/**", "/auth/vk", "/help/**", "/favicon.ico").permitAll()
                .anyRequest().hasAnyRole("USER", "CREATOR", "ADMIN", "MODER")
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/auth")
                .and()
                .logout().logoutUrl("/logout");
    }
}
