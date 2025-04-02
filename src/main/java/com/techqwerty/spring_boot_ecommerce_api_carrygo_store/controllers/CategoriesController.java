package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryUpdateDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
@Tag(name = "CRUD REST APIs for Category Resource", description = "CRUD REST API - Create category, Update category, Get category, Get All categories, Delete category")
public class CategoriesController {

    // inject the CategoryService
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        var savedCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(savedCategories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId) {
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryUpdateDto> updateCategory(@PathVariable("id") Long categoryId,
            @RequestBody @Valid CategoryUpdateDto categoryDto) throws Exception {
        categoryDto.setId(categoryDto.getId()); // set the field to be updated
        categoryDto.setName(categoryDto.getName()); // set the field to be updated
        CategoryUpdateDto updatedCategoryDto = categoryService.updateCategory(categoryDto);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) throws Exception {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted successfully!", HttpStatus.OK);
    }

}
