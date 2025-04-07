package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddDto {
    @NotNull(message = "Product name is required")
    @Length(max = 100, message = "Product name cannot be more than 100 characters")
    private String name;
    @NotNull(message = "Product detail is required")
    @Length(max = 1000, message = "Product detail cannot be more than 1000 characters")
    private String details;

    // @Transient // will not be saved in DB
    @JsonIgnore // will not appear in JSON
    @NotNull(message = "Product image is required")
    private MultipartFile file;
    private String imageUrl; // will store the file URL
    @NotNull(message = "Product price is required")
    private double price;
    @NotNull(message = "Product price is required")
    private int categoryId;
}
