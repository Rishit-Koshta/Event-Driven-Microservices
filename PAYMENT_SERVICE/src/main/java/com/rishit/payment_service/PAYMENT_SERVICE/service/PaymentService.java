package com.rishit.payment_service.PAYMENT_SERVICE.service;

import com.rishit.payment_service.PAYMENT_SERVICE.entity.Payment;
import com.rishit.payment_service.PAYMENT_SERVICE.entity.PaymentStatus;
import com.rishit.payment_service.PAYMENT_SERVICE.producer.PaymentEventProducer;
import com.rishit.payment_service.PAYMENT_SERVICE.event.OrderCreatedEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.event.PaymentCompleteEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.event.PaymentFailedEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer producer;

    public void processPayment(
            OrderCreatedEvent event) {

        boolean paymentSuccess = true;

        if (paymentSuccess) {

            Payment payment =
                    paymentRepository.save(
                            Payment.builder()
                                    .orderId(event.getOrderId())
                                    .amount(event.getAmount())
                                    .status(PaymentStatus.SUCCESS)
                                    .createdAt(LocalDateTime.now())
                                    .build()
                    );

            producer.publishPaymentCompleted(
                    PaymentCompleteEvent.builder()
                            .paymentId(payment.getId())
                            .orderId(payment.getOrderId())
                            .amount(payment.getAmount())
                            .build()
            );

        } else {

            producer.publishPaymentFailed(
                    PaymentFailedEvent.builder()
                            .orderId(event.getOrderId())
                            .reason("Insufficient funds")
                            .build()
            );
        }
    }
}