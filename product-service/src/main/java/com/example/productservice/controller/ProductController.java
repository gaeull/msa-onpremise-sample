package com.example.productservice.controller;

import com.example.common.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return List.of(
            new ProductDto("1", "MacBook Pro", 3500000),
            new ProductDto("2", "iPad Air", 900000),
            new ProductDto("3", "Galaxy S24", 1200000),
            new ProductDto("4", "test CI 2", 950123220),
            new ProductDto("5", "test CI 3", 1000000)
        );
    }
}
