package com.account.photo.repository;

import com.account.photo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByPassword(String password);
    User findByUsernameOrVkId(String username, long vkId);
}
