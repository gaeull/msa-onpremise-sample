package com.example.orderservice.client;

import com.example.common.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public List<ProductDto> getProducts() {
        return Collections.singletonList(
                new ProductDto("fallback", "상품 정보를 불러올 수 없습니다", 0)
        );
    }
}