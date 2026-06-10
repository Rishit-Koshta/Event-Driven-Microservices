package com.rishit.orderservice.ORDER_SERVICE.kafka;

import com.rishit.orderservice.ORDER_SERVICE.event.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreateEvent(OrderCreateEvent event){

        kafkaTemplate.send(
          "order-created",
          event.getOrderId().toString(),
                event
        );
    }
}
