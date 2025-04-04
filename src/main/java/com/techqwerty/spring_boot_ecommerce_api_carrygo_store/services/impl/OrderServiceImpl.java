package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderByUserDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderDetailsDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Category;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.CategoryRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.OrderRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.OrderService;

import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    // inject the category JPA repository
    private OrderRepository orderRepository;
    // inhect the Model Mapper for mapping
    private ModelMapper modelMapper;

    @Override
    public List<OrderDetailsDto> getOrderDetailsByOrderId(Long orderId) {
        List<OrderDetailsDto> orders = orderRepository.getOrderDetailsByOrderId(orderId);
        if (orders.size() <= 0)
            return Collections.emptyList();
        return orders;
    }

    @Override
    public List<OrderByUserDto> getOrdersByUserId(Long userId) {
        List<OrderByUserDto> orders = orderRepository.getOrdersByUserId(userId);
        if (orders.size() <= 0)
            return Collections.emptyList();
        return orders;
    }

    // TODO: Add endpoint to place an order

}
