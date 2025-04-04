package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderByUserDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderDetailsDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
@Tag(name = "REST APIs for for Order Resource", description = "Endpoints - get order details, get orders by user, add order")
public class OrdersController {

    // inject the order service
    private OrderService orderService;

    @Operation(summary = "Get order details REST API", description = "Get order details REST API is used to get order details from the database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @SecurityRequirement(name = "Bear Authentication")
    @GetMapping("getOrderDetailsByOrderId/{orderId}")
    @PreAuthorize("hasRole('ADMIN', 'USER', 'MANAGER')")
    public ResponseEntity<List<OrderDetailsDto>> getOrderDetailsByOrderId(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(orderService.getOrderDetailsByOrderId(orderId), HttpStatus.OK);
    }

    @Operation(summary = "Get orders by user id REST API", description = "Get orders by user id REST API is used to get orders from the database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @SecurityRequirement(name = "Bear Authentication")
    @GetMapping("getOrdersByUserId/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<OrderByUserDto>> getOrdersByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }
}
