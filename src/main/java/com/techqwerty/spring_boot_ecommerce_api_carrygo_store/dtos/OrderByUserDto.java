package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import java.time.LocalDateTime;

public interface OrderByUserDto {
    int getId();

    double getOrderTotal();

    LocalDateTime getOrderPlaced();
}
