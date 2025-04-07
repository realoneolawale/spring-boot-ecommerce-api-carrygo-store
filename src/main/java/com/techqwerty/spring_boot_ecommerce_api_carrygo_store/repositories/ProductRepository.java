package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
