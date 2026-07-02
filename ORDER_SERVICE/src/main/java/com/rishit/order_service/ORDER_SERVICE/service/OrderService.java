package com.rishit.order_service.ORDER_SERVICE.service;


import com.rishit.common.event.OrderCreateEvent;

import com.rishit.order_service.ORDER_SERVICE.client.UserClient;
import com.rishit.order_service.ORDER_SERVICE.dto.OrderRequest;
import com.rishit.order_service.ORDER_SERVICE.dto.OrderResponse;
import com.rishit.order_service.ORDER_SERVICE.dto.UserResponse;
import com.rishit.order_service.ORDER_SERVICE.entity.Order;
import com.rishit.order_service.ORDER_SERVICE.entity.OrderStatus;
import com.rishit.order_service.ORDER_SERVICE.kafka.OrderEventProducer;
import com.rishit.order_service.ORDER_SERVICE.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor


public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer producer;
    private final UserClient userClient;

    public OrderResponse createOrder(OrderRequest request){

        UserResponse user =
                userClient.getUser(request.getUserId());

        Order order = Order.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        Order saveOrder = orderRepository.save(order);

        OrderCreateEvent event = OrderCreateEvent.builder()
                .orderId(saveOrder.getId())
                .userId(saveOrder.getUserId())
                .amount(saveOrder.getAmount())
                .build();

        producer.publishOrderCreateEvent(event);

        return OrderResponse.builder()
                .orderId(saveOrder.getId())
                .amount(saveOrder.getAmount())
                .status(saveOrder.getStatus().name())
                .build();
    }
}
