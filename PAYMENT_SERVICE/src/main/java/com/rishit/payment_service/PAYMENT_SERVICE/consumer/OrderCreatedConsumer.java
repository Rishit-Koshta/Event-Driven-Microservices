package com.rishit.payment_service.PAYMENT_SERVICE.consumer;
import com.rishit.common.event.OrderCreateEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.entity.ProcessedEvent;
import com.rishit.payment_service.PAYMENT_SERVICE.repository.ProcessedEventRepository;
import com.rishit.payment_service.PAYMENT_SERVICE.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final PaymentService paymentService;
    private final ProcessedEventRepository processedEventRepository;

    @Transactional
    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group"
    )
    public void consume(OrderCreateEvent event) {

        System.out.println(
                "Received Order Event : "
                        + event.getOrderId()
        );

        // Duplicate check
        if (processedEventRepository.existsById(event.getEventId())) {

            System.out.println(
                    "Duplicate event ignored : "
                            + event.getEventId()
            );

            return;
        }

        // Process payment
        paymentService.processPayment(event);

        // Mark event as processed
        ProcessedEvent processedEvent =
                ProcessedEvent.builder()
                        .eventId(event.getEventId())
                        .processedAt(LocalDateTime.now())
                        .build();

        processedEventRepository.save(processedEvent);

        System.out.println(
                "Event processed successfully."
        );
    }
}