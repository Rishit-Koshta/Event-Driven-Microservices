package com.rishit.payment_service.PAYMENT_SERVICE.consumer;

import com.rishit.payment_service.PAYMENT_SERVICE.event.OrderCreatedEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final PaymentService paymentService;

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group"
    )
    public void consume(
            OrderCreatedEvent event) {

        paymentService.processPayment(event);
    }
}