package com.rishit.payment_service.PAYMENT_SERVICE.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCompleteEvent {

    private Long paymentId;
    private Long orderId;
    private Double amount;
}