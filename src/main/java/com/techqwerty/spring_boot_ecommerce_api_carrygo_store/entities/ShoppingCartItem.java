package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities;

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
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private int qty;
    private double totalAmount;
    // private int product_id;
    // private int user_id;

    // shopping_cart_items belongs to a Product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // shopping_cart_items belongs to a User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
