package com.rishit.payment_service.PAYMENT_SERVICE.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessedEvent {


    @Id
    private String eventId;


    private LocalDateTime processedAt;
}