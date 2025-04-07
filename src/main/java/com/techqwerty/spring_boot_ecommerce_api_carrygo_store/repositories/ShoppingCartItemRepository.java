package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ShoppingCartItemGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.ShoppingCartItem;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {

    @Query(value = "SELECT sci.id, sci.price, sci.total_amount AS totalAmount, sci.qty, p.id AS productId, p.name AS productName, p.image_url AS imageUrl FROM shopping_cart_items sci INNER JOIN products p ON sci.product_id = p.id WHERE sci.user_id = :userId;", nativeQuery = true)
    List<ShoppingCartItemGetDto> getUserShoppingCartItems(@Param("userId") Long userId);

    // Using JPQL
    @Query(value = "SELECT s FROM ShoppingCartItem s WHERE s.user.id = :userId AND s.product.id = :productId")
    ShoppingCartItem findByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Long userId);

}
