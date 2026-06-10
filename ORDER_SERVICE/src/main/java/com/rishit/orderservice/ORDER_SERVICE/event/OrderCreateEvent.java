package com.rishit.orderservice.ORDER_SERVICE.event;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateEvent {

    private Long orderId;
    private Long userId;
    private Double amount;

}
