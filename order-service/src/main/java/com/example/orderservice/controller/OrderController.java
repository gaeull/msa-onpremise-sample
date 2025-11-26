package com.example.orderservice.controller;

import com.example.common.event.OrderCreatedEvent;
import com.example.orderservice.client.ProductClient;
import com.example.common.dto.ProductDto;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.orderservice.kafka.OrderKafkaProducer;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name="주문 API", description = "주문 관련 API입니다")
public class OrderController {
    private final ProductClient productClient;
    private final OrderKafkaProducer orderKafkaProducer;

    // @GetMapping("/orders/products")
    @GetMapping("/products")
    public List<ProductDto> getProductsFromProductService() {
        return productClient.getProducts();
    }

    @GetMapping("/orders")
    @Operation(
            summary = "주문 목록 조회",
            description = "현재 등록된 모든 주문 정보를 반환합니다. 각 주문에는 주문번호, 주문자명, 상품 목록이 포함됩니다."
            )
    @ApiResponse(responseCode = "200", description = "주문 목록 조회 성공")
    public List<OrderDto> getOrders() {
        //현재는 김순곤님의 1번 주문만 반환 (테스트용)
        List<ProductDto> products = productClient.getProducts();

        return List.of(
                new OrderDto("1", "김순곤", products),
                new OrderDto("2", "문가을", products),
                new OrderDto("3", "테스트", products),
                new OrderDto("4", "테스트", products)
        );
    }

    // 주문하기
    @PostMapping
    public String createOrder(@RequestBody CreateOrderRequest request) {
        String orderId = UUID.randomUUID().toString();

        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.getProductId(),
                request.getQuantity()
        );

        orderKafkaProducer.send(event);

        return "Order Success(OrderId = " + orderId + ")";
    }
}
