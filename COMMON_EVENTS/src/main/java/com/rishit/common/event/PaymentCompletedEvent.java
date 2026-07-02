package com.rishit.common.event;

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
    private Long productId;
}