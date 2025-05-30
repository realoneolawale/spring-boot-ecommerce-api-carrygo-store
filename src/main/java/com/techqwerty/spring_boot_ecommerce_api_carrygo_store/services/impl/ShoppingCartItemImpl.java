package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.impl;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderDetailsDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemAddDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Product;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.ProductSize;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.ShoppingCartItem;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.User;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions.EcommerceAPIException;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.ProductRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.ProductSizeRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.ShoppingCartItemRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.UserRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.ShoppingCartItemService;

import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShoppingCartItemImpl implements ShoppingCartItemService {

    private ShoppingCartItemRepository shoppingCartItemRepository;
    private ProductRepository productRepository;
    private ProductSizeRepository productSizeRepository;
    private UserRepository userRepository;

    @Override
    public List<ShoppingCartItemGetDto> getUserShoppingCartItems(Long userId) {
        List<ShoppingCartItemGetDto> items = shoppingCartItemRepository.getUserShoppingCartItems(userId);
        if (items.size() <= 0)
            return Collections.emptyList();
        return items;
    }

    @Override
    public String addUserShoppingCartItem(ShoppingCartItemAddDto shoppingCartItemAddDto) {
        ShoppingCartItem item = shoppingCartItemRepository.findByProductIdAndUserId(
                shoppingCartItemAddDto.getProductId(),
                shoppingCartItemAddDto.getUserId());
        if (item != null) { // item is already in cart
            item.setQty(item.getQty() + shoppingCartItemAddDto.getQty());
            item.setTotalAmount(item.getTotalAmount() + shoppingCartItemAddDto.getPrice()); // increase the totalAmount
                                                                                            // by adding the price
            shoppingCartItemRepository.save(item);
            return "Item updated";
        } else { // add new item to shopping cart
            Product product = productRepository.findById(shoppingCartItemAddDto.getProductId())
                    .orElseThrow();
            ProductSize productSize = productSizeRepository.findById(shoppingCartItemAddDto.getSizeId())
                    .orElseThrow();
            
            User user = userRepository.findById(shoppingCartItemAddDto.getUserId())
                    .orElseThrow();
            ShoppingCartItem newItem = new ShoppingCartItem();
            newItem.setPrice(shoppingCartItemAddDto.getPrice());
            newItem.setQty(shoppingCartItemAddDto.getQty());
            newItem.setTotalAmount(shoppingCartItemAddDto.getPrice() * shoppingCartItemAddDto.getQty());
            newItem.setProduct(product);
            newItem.setUser(user);
            newItem.setProductSize(productSize);
            shoppingCartItemRepository.save(newItem);
            return "Item added to cart";
        }
    }
    @Override
    public String deleteUserShoppingCartItem(Long productId, Long userId) {
        ShoppingCartItem item = shoppingCartItemRepository.findByProductIdAndUserId(productId, userId);
        if (item != null) { // item is already in cart                                                                        // by adding the price
            shoppingCartItemRepository.delete(item);
            return "Item deleted";
        }
        throw new EcommerceAPIException("Item not found", HttpStatus.BAD_REQUEST);
    }

    @Override
    public String updateUserShoppingCartItem(Long productId, Long userId, String action) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        ShoppingCartItem item = shoppingCartItemRepository.findByProductIdAndUserId(
                productId,
                user.getId());

        if (item != null) { // item is already in cart
            if (action.toLowerCase().equals("increase")) {
                item.setQty(item.getQty() + 1);
            } else if (action.toLowerCase().equals("decrease")) {
                if (item.getQty() > 1) {
                    item.setQty(item.getQty() - 1);
                } else {
                    shoppingCartItemRepository.delete(item);
                    return "Item deleted from cart";
                }
            } else if (action.toLowerCase().equals("delete")) {
                shoppingCartItemRepository.delete(item);
                return "Item removed from cart";
            } else {
                throw new EcommerceAPIException("Invalid request", HttpStatus.BAD_REQUEST);
            }
            item.setTotalAmount(item.getPrice() * item.getQty());
            shoppingCartItemRepository.save(item);
            return "Cart item updated";
        } else {
            return "Bad request. Item not found in cart";
        }
    }
}
