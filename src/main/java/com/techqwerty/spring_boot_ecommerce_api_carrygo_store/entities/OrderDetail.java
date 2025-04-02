package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int qty;
    @Column(nullable = false)
    private double totalAmount;

    // orderDetail belongs to order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // orderDetail belongs to product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
