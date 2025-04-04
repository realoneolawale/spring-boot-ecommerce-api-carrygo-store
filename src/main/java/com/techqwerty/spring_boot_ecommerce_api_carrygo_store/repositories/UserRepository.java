package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}