package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.impl;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderDetailsDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemAddDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Product;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.ShoppingCartItem;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.User;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions.EcommerceAPIException;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.ProductRepository;
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
            item.setTotalAmount(item.getTotalAmount() + shoppingCartItemAddDto.getTotalAmount());
            shoppingCartItemRepository.save(item);
            return "Item updated";
        } else { // add new item to shopping cart
            Product product = productRepository.findById(shoppingCartItemAddDto.getProductId())
                    .orElseThrow();
            User user = userRepository.findById(shoppingCartItemAddDto.getUserId())
                    .orElseThrow();
            ShoppingCartItem newItem = new ShoppingCartItem();
            newItem.setPrice(shoppingCartItemAddDto.getPrice());
            newItem.setQty(shoppingCartItemAddDto.getQty());
            newItem.setTotalAmount(shoppingCartItemAddDto.getTotalAmount());
            newItem.setProduct(product);
            newItem.setUser(user);
            shoppingCartItemRepository.save(newItem);
            return "Item added to cart";
        }
    }
}
