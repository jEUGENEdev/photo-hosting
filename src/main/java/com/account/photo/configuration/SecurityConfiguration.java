package com.account.photo.configuration;

import com.account.photo.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Value("${upload.photo.url}")
    private String uploadPhotoUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .authorizeRequests()
                .antMatchers("/", "/static/**", String.format("%s/**", uploadPhotoUrl), "/login", "/auth",
                        "/archive", "/photos/*").permitAll()
                .anyRequest().hasAnyRole("USER")
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/auth")
                .and()
                .logout().logoutUrl("/logout");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
