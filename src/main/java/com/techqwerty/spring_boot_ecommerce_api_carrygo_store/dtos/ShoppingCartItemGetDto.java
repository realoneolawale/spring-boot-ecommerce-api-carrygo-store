package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

public interface ShoppingCartItemGetDto {
    int getId();

    double getPrice();

    double getTotalAmount();

    int getQty();

    int getProductId();

    String getProductName();

    String getImageUrl();
}
