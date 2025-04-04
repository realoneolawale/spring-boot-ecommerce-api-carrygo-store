package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String email;
    private String password;
}
