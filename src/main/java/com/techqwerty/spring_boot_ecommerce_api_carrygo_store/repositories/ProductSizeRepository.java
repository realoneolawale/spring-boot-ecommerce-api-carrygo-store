package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.ProductSize;

public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
    List<ProductSize> findByProductId(Long productId);
}
