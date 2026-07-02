package com.rishit.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentFailedEvent {

    private Long orderId;
    private Long userId;
    private String reason;
}