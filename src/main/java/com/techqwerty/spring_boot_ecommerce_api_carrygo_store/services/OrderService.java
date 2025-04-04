package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services;

import java.util.List;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderByUserDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderDetailsDto;

public interface OrderService {
    List<OrderDetailsDto> getOrderDetailsByOrderId(Long orderId);

    List<OrderByUserDto> getOrdersByUserId(Long userId);

    // TODO: Add endpoint to place an order

}
