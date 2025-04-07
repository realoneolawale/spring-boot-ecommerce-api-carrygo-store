package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ProductAddDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ProductGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.enums.ProductCategoryEnum;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
@Tag(name = "REST APIs for for Product Resource", description = "Endpoints - add product to inventory")
public class ProductsController {

    private ProductService productService;

    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add product with metadata")
    @PostMapping(value = "/add-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductAddDto> createProduct(
            @RequestParam MultipartFile file,
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam String details,
            @RequestParam ProductCategoryEnum category)
            throws IOException {
        ProductAddDto dtoToSave = new ProductAddDto(name, details, file, "", price, category.getValue());
        ProductAddDto dtoToReturn = productService.createProduct(dtoToSave);
        return new ResponseEntity<>(dtoToReturn, HttpStatus.CREATED);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductGetDto> getProduct(@PathVariable Long productId) {
        ProductGetDto product = productService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    // GET: http://localhost:8081/api/products
    // GET: http://localhost:8081/api/products/bestselling/0
    @GetMapping({ "", "/{productType}/{categoryId}" })
    public ResponseEntity<List<ProductGetDto>> getProducts(
            @Schema(description = "Product type can be one of 3 options: category, trending or bestselling") @PathVariable(required = false) String productType,
            @Schema(description = "Product category can be any existing category id: 1, 2, 3 and so on") @PathVariable(required = false) Integer categoryId) {
        List<ProductGetDto> products = productService.getAllProducts(productType, categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
