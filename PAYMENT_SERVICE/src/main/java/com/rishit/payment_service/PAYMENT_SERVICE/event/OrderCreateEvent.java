package com.rishit.payment_service.PAYMENT_SERVICE.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateEvent {

    private Long orderId;
    private Long userId;
    private Double amount;
}