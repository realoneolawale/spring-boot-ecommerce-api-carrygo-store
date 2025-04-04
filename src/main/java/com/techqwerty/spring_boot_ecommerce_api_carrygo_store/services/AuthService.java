package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.LoginDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
