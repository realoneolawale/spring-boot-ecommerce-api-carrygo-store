package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.CategoryGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.OrderDetailsDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ProductAddDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.ProductGetDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Category;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Product;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions.EcommerceAPIException;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions.NameAlreadyException;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.CategoryRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.ProductRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.ProductService;
import io.jsonwebtoken.lang.Collections;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    private final String UPLOAD_DIR = "products/";

    @Override
    public ProductAddDto createProduct(ProductAddDto productAddDto) throws IOException {
        // check if the product already exists
        Optional<Product> optionalProduct = productRepository.findByName(productAddDto.getName());
        if (optionalProduct.isPresent()) {
            throw new NameAlreadyException("Product already exist");
        }
        // check if the product category exists
        int categoryId = productAddDto.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EcommerceAPIException("Category not found", HttpStatus.NOT_FOUND));

        Product product = new Product();
        product.setName(productAddDto.getName());
        product.setDetails(productAddDto.getDetails());
        product.setPrice(productAddDto.getPrice());
        product.setTrending(false);
        product.setBestSelling(false);
        product.setCategory(category);
        // Product product = modelMapper.map(productAddDto, Product.class);
        // upload the product image
        MultipartFile file = productAddDto.getFile(); // file.getFile();
        // save the file
        Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        // set the image url to the uploaded file
        product.setImageUrl("/products/" + file.getOriginalFilename());
        // save product in the DB
        try {
            Product savedProduct = productRepository.save(product);
            ProductAddDto savedProductDto = modelMapper.map(savedProduct, ProductAddDto.class);
            return savedProductDto;
        } catch (Exception e) {
            System.out.println("ERROR OCCURED: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ProductGetDto> getAllProducts(@Nullable String productType, @Nullable Integer categoryId) {
        List<Product> products = productRepository.findAll();
        // if there are no products, return an empty list
        if (!(products.size() > 0)) {
            return Collections.emptyList();
        }
        // filter the products
        if (productType != null) {
            if (productType.equals("category") && categoryId != null) {
                products = products.stream()
                        .filter(p -> p.getCategory() != null && p.getCategory().getId() == categoryId.intValue())
                        .collect(Collectors.toList());
            } else if (productType.equals("trending")) {
                products = products.stream()
                        .filter(p -> p.isTrending() == true)
                        .collect(Collectors.toList());
            } else if (productType.equals("bestselling")) {
                products = products.stream()
                        .filter(p -> p.isBestSelling() == true)
                        .collect(Collectors.toList());
            }
        }
        // return list of DTOs
        return products.stream().map((product) -> modelMapper.map(product, ProductGetDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductGetDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new EcommerceAPIException("Product not found", HttpStatus.NOT_FOUND));
        return modelMapper.map(product, ProductGetDto.class);
    }

}
