package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services;

import java.io.IOException;
import java.util.List;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ProductAddDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ProductGetDto;
import jakarta.annotation.Nullable;

public interface ProductService {
    ProductAddDto createProduct(ProductAddDto productAddDto) throws IOException;

    List<ProductGetDto> getAllProducts(@Nullable String productType, @Nullable Integer categoryId);

    ProductGetDto getProduct(Long productId);
}
