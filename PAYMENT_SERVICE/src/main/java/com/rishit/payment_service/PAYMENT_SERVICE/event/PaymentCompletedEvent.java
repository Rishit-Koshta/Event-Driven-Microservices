package com.rishit.payment_service.PAYMENT_SERVICE.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCompletedEvent {

    private Long orderId;
    private Long userId;
    private Double amount;
}