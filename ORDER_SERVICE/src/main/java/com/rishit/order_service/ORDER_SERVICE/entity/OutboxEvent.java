package com.rishit.order_service.ORDER_SERVICE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutboxEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String aggregateType;


    private Long aggregateId;


    private String eventType;


    @Column(columnDefinition = "TEXT")
    private String payload;


    @Enumerated(EnumType.STRING)
    private OutboxStatus status;


    private LocalDateTime createdAt;
}