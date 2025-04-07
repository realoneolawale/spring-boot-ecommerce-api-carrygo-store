package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemAddDto {
    @Positive(message = "Price must be greater than 0")
    private double price;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int qty;
    @Positive(message = "Total amount must be greater than 0")
    private double totalAmount;
    @Positive(message = "Product is not selected")
    private Long productId;
    @Positive(message = "User is not selected")
    private Long userId;
}
