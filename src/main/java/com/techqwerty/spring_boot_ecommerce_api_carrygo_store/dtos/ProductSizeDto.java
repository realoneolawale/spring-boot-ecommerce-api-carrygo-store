package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSizeDto {
    private Long id;
    private String size;
    private int stock;
}
