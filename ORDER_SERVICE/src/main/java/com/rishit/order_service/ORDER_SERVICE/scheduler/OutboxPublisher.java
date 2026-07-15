package com.rishit.order_service.ORDER_SERVICE.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishit.common.event.OrderCreateEvent;
import com.rishit.order_service.ORDER_SERVICE.entity.OutboxEvent;
import com.rishit.order_service.ORDER_SERVICE.entity.OutboxStatus;
import com.rishit.order_service.ORDER_SERVICE.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {


    private final OutboxRepository repository;

    private final KafkaTemplate<String,Object> kafkaTemplate;

    private final ObjectMapper objectMapper;


    @Scheduled(fixedRate = 5000)
    public void publish(){


        List<OutboxEvent> events =
                repository.findByStatus(
                        OutboxStatus.PENDING
                );


        for(OutboxEvent outbox : events){


            try {


                OrderCreateEvent event =
                        objectMapper.readValue(
                                outbox.getPayload(),
                                OrderCreateEvent.class
                        );


                kafkaTemplate.send(
                        "order-created",
                        event
                );


                outbox.setStatus(
                        OutboxStatus.SENT
                );


                repository.save(outbox);


            }
            catch(Exception e){

                System.out.println(
                        "Outbox publishing failed"
                );

            }
        }
    }
}