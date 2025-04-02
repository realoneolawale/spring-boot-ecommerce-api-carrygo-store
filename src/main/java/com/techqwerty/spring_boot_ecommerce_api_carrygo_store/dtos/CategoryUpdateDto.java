package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDto {
    @NotNull(message = "Category id is required")
    private Long id;
    @NotEmpty(message = "Category name is required")
    private String name;
}
