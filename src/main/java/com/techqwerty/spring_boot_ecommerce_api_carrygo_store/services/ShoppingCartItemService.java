package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services;

import java.util.List;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemAddDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemGetDto;

public interface ShoppingCartItemService {
    List<ShoppingCartItemGetDto> getUserShoppingCartItems(Long userId);

    String addUserShoppingCartItem(ShoppingCartItemAddDto shoppingCartItemAddDto);

    String updateUserShoppingCartItem(Long productId, Long userId, String action);
}
