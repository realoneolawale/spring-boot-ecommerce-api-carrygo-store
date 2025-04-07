package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // query method to get a category by name
    Optional<Category> findByName(String name);
}
