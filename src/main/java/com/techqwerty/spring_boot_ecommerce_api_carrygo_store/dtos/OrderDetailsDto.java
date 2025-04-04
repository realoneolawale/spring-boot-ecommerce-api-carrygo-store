package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
    private Long id;
    private int qty;
    private double subTotal;
    private String productName;
    private String productImage;
    private double productPrice;
}
