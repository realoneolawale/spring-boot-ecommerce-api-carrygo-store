package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetDto {
    private Long id;
    private String name;
    private String details;
    private String imageUrl;
    private double price;
    private boolean isTrending;
    private boolean isBestSelling;
    private int categoryId;
    private List<ProductSizeDto> sizes = new ArrayList<>();
}
