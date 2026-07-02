package com.rishit.order_service.ORDER_SERVICE.controller;

import com.rishit.order_service.ORDER_SERVICE.dto.OrderRequest;
import com.rishit.order_service.ORDER_SERVICE.dto.OrderResponse;
import com.rishit.order_service.ORDER_SERVICE.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }
}
