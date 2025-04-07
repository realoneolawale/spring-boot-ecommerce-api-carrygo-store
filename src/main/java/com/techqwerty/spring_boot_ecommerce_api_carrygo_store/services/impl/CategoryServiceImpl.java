package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryUpdateDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Category;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions.EcommerceAPIException;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions.NameAlreadyException;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions.ResourceNotFoundException;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.CategoryRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.CategoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    // inject the category JPA repository
    private CategoryRepository categoryRepository;
    // inhect the Model Mapper for mapping
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDto.getName());
        if (optionalCategory.isPresent()) {
            throw new NameAlreadyException("Category name already exist");
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        CategoryDto savedcCategoryDto = modelMapper.map(savedCategory, CategoryDto.class);
        return savedcCategoryDto;
    }

    @Override
    public List<CategoryGetDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> modelMapper.map(category, CategoryGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryGetDto getCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new EcommerceAPIException("Category not found", HttpStatus.NOT_FOUND));
        return modelMapper.map(category, CategoryGetDto.class);
    }

    @Override
    public CategoryUpdateDto updateCategory(CategoryUpdateDto categoryDto) throws Exception {
        Category existingCategory = categoryRepository.findById(categoryDto.getId()).orElseThrow(
                () -> new Exception("Category not found"));
        existingCategory.setName(categoryDto.getName());
        // set other fields if others
        Category updatedCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(updatedCategory, CategoryUpdateDto.class);
    }

    @Override
    public void deleteCategory(int categoryId) throws Exception {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new Exception("Category not found"));
        categoryRepository.deleteById(existingCategory.getId());
    }

}
