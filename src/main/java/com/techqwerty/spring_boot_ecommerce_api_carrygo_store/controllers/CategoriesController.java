package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryUpdateDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Create Category REST API", description = "Create Category REST API is used to save category into database")
    @ApiResponse(responseCode = "201", description = "Http Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryGetDto>> getAllCategories() {
        var savedCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(savedCategories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryGetDto> getCategoryById(@PathVariable("id") int categoryId) {
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @Operation(summary = "Update Category REST API", description = "Update Category REST API is used to update category database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryUpdateDto> updateCategory(@PathVariable("id") Long categoryId,
            @RequestBody @Valid CategoryUpdateDto categoryDto) throws Exception {
        categoryDto.setId(categoryDto.getId()); // set the field to be updated
        categoryDto.setName(categoryDto.getName()); // set the field to be updated
        CategoryUpdateDto updatedCategoryDto = categoryService.updateCategory(categoryDto);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @Operation(summary = "Delete Category REST API", description = "Delete Category REST API is used to delete category in the database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int categoryId) throws Exception {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted successfully!", HttpStatus.OK);
    }

}
