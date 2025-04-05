package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

public interface OrderDetailsDto {
    Long getId();

    int getQty();

    double getSubTotal();

    String getProductName();

    String getProductImage();

    double getProductPrice();
}
