package com.saara.userservice.repository;

import com.saara.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    default boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }
}
