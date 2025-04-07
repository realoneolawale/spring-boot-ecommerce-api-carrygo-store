package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemAddDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.ShoppingCartItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/shopping")
@Tag(name = "REST APIs for for Shopping Cart Item Resource", description = "Endpoints - get items from cart, add items to cart, update and delete items from cart")
public class ShoppingCartItemController {

    private ShoppingCartItemService shoppingCartItemService;

    @GetMapping("{userId}")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<ShoppingCartItemGetDto>> getUserShoppingCartItems(@PathVariable Long userId) {
        List<ShoppingCartItemGetDto> items = shoppingCartItemService.getUserShoppingCartItems(userId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("add-to-cart")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> addUserShoppingCartItem(
            @RequestBody @Validated ShoppingCartItemAddDto shoppingCartItemAddDto) {
        String response = shoppingCartItemService.addUserShoppingCartItem(shoppingCartItemAddDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
