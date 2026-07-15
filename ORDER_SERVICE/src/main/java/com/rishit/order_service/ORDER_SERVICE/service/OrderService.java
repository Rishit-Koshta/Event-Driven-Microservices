package com.rishit.order_service.ORDER_SERVICE.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishit.common.event.OrderCreateEvent;
import com.rishit.order_service.ORDER_SERVICE.dto.OrderRequest;
import com.rishit.order_service.ORDER_SERVICE.dto.OrderResponse;
import com.rishit.order_service.ORDER_SERVICE.dto.UserResponse;
import com.rishit.order_service.ORDER_SERVICE.entity.Order;
import com.rishit.order_service.ORDER_SERVICE.entity.OrderStatus;
import com.rishit.order_service.ORDER_SERVICE.entity.OutboxEvent;
import com.rishit.order_service.ORDER_SERVICE.entity.OutboxStatus;
import com.rishit.order_service.ORDER_SERVICE.kafka.OrderEventProducer;
import com.rishit.order_service.ORDER_SERVICE.repository.OrderRepository;
import com.rishit.order_service.ORDER_SERVICE.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor


public class OrderService {

    private final OrderRepository orderRepository;

    private final UserClientService userClientService;
    private final OutboxRepository outboxRepository;

    private final ObjectMapper objectMapper;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        UserResponse user =
                userClientService.getUser(
                        request.getUserId()
                );


        Order order = Order.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();


        Order saveOrder =
                orderRepository.save(order);


        OrderCreateEvent event =
                OrderCreateEvent.builder()
                        .eventId(UUID.randomUUID().toString())
                        .orderId(saveOrder.getId())
                        .userId(saveOrder.getUserId())
                        .productId(saveOrder.getProductId())
                        .amount(saveOrder.getAmount())
                        .build();


        String payload;

        try {

            payload =
                    objectMapper.writeValueAsString(event);

        } catch (JsonProcessingException e) {

            throw new RuntimeException(
                    "Failed to serialize order event",
                    e
            );
        }


        OutboxEvent outbox =
                OutboxEvent.builder()

                        .aggregateType("ORDER")

                        .aggregateId(saveOrder.getId())

                        .eventType("OrderCreated")

                        .payload(payload)

                        .status(OutboxStatus.PENDING)

                        .createdAt(LocalDateTime.now())

                        .build();


        outboxRepository.save(outbox);


        return OrderResponse.builder()
                .orderId(saveOrder.getId())
                .amount(saveOrder.getAmount())
                .status(saveOrder.getStatus().name())
                .build();
    }
}