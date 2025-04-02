package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services;

import java.util.List;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryUpdateDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long categoryId);

    CategoryUpdateDto updateCategory(CategoryUpdateDto categoryDto) throws Exception;

    void deleteCategory(Long categoryId) throws Exception;

}