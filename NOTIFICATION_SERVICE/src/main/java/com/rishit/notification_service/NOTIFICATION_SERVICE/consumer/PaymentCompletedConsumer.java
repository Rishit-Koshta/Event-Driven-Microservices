package com.rishit.notification_service.NOTIFICATION_SERVICE.consumer;

import com.rishit.common.event.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentCompletedConsumer {

    @KafkaListener(
            topics = "payment-completed",
            groupId = "notification-group"
    )
    public void consume(PaymentCompletedEvent event){

        System.out.println("--------------------------------");

        System.out.println("EMAIL SENT");

        System.out.println(
                "Payment successful for order "
                        + event.getOrderId()
        );

        System.out.println("--------------------------------");
    }
}