package com.example.orderservice.client;

import com.example.common.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Eureka의 서비스명으로 통신함.
@FeignClient (name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDto> getProducts();
}
