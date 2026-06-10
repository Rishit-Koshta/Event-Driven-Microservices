package com.rishit.orderservice.ORDER_SERVICE.service;


import com.rishit.orderservice.ORDER_SERVICE.dto.OrderRequest;
import com.rishit.orderservice.ORDER_SERVICE.dto.OrderResponse;
import com.rishit.orderservice.ORDER_SERVICE.entity.Order;
import com.rishit.orderservice.ORDER_SERVICE.entity.OrderStatus;
import com.rishit.orderservice.ORDER_SERVICE.event.OrderCreateEvent;
import com.rishit.orderservice.ORDER_SERVICE.kafka.OrderEventProducer;
import com.rishit.orderservice.ORDER_SERVICE.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer producer;


    public OrderResponse createOrder(OrderRequest request){

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
