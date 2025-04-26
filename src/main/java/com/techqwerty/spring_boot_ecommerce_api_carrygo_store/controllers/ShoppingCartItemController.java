package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemAddDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.ShoppingCartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @DeleteMapping("delete-cart-item/{productId}/{userId}")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Delete product from cart (USER ONLY)")
    public ResponseEntity<String> deleteUserShoppingCartItem(@PathVariable Long productId, @PathVariable Long userId) {
        String response = shoppingCartItemService.deleteUserShoppingCartItem(productId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update-cart/{productId}/{userId}/{action}")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Update product in cart (USER ONLY)")
    public ResponseEntity<String> updateUserShoppingCartItem(
            @PathVariable Long userId, @PathVariable Long productId,

            @Schema(description = "action to take on product in cart (e.g. decrease, increase or delete)") @PathVariable String action) {
        String response = shoppingCartItemService.updateUserShoppingCartItem(productId, userId, action);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
