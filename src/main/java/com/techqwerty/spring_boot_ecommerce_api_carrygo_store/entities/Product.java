package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String details;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private boolean isTrending;
    @Column(nullable = false)
    private boolean isBestSelling;

    // product has a list of orderDetails
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    // product belongs to a category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // product has a list of shoppingCartItems
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ShoppingCartItem> shoppingCartItems;

}
