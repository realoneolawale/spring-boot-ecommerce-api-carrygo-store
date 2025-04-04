package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderByUserDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderDetailsDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT od.id, od.qty, od.total_amount AS subTotal, p.name AS productName, p.image_url as productImage, p.price AS productPrice  FROM order_details od INNER JOIN orders ord ON od.order_id = ord.id INNER JOIN products p ON od.product_id = p.id WHERE od.id = :orderId", nativeQuery = true)
    List<OrderDetailsDto> getOrderDetailsByOrderId(Long orderId);

    @Query(value = "SELECT ord.id, ord.order_total AS orderTotal, ord.order_placed AS orderPlaced FROM orders ord WHERE ord.user_id = :userId;", nativeQuery = true)
    List<OrderByUserDto> getOrdersByUserId(Long userId);

}
