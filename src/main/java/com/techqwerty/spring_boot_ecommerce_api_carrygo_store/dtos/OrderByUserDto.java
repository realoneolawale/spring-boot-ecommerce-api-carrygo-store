package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderByUserDto {
    private int id;
    private double orderTotal;
    private LocalDateTime orderPlaced;
}
