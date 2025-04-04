package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
