package com.rishit.payment_service.PAYMENT_SERVICE.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_db")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDateTime createdAt;
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
