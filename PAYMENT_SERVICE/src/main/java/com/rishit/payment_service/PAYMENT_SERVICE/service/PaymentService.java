package com.rishit.payment_service.PAYMENT_SERVICE.service;

import com.rishit.payment_service.PAYMENT_SERVICE.entity.Payment;
import com.rishit.payment_service.PAYMENT_SERVICE.entity.PaymentStatus;
import com.rishit.payment_service.PAYMENT_SERVICE.event.PaymentCompletedEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.producer.PaymentEventProducer;
import com.rishit.payment_service.PAYMENT_SERVICE.event.OrderCreateEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.event.PaymentFailedEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentEventProducer producer;

    public void processPayment(
            OrderCreateEvent event) {

        boolean success = Math.random() > 0.3;

        if(success){

            Payment payment =
                    Payment.builder()
                            .orderId(event.getOrderId())
                            .userId(event.getUserId())
                            .amount(event.getAmount())
                            .status(PaymentStatus.SUCCESS)
                            .build();

            repository.save(payment);

            producer.publishPaymentCompleted(
                    PaymentCompletedEvent.builder()
                            .orderId(event.getOrderId())
                            .userId(event.getUserId())
                            .amount(event.getAmount())
                            .build()
            );

            System.out.println(
                    "Payment completed for order "
                            + event.getOrderId()
            );

        } else {

            Payment payment =
                    Payment.builder()
                            .orderId(event.getOrderId())
                            .userId(event.getUserId())
                            .amount(event.getAmount())
                            .status(PaymentStatus.FAILED)
                            .build();

            repository.save(payment);

            producer.publishPaymentFailed(
                    PaymentFailedEvent.builder()
                            .orderId(event.getOrderId())
                            .userId(event.getUserId())
                            .reason("Payment declined")
                            .build()
            );

            System.out.println(
                    "Payment failed for order "
                            + event.getOrderId()
            );
        }
    }
}