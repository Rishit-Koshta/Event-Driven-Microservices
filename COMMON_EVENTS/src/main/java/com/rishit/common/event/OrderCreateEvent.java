package com.rishit.common.event;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateEvent {


    private String eventId;


    private Long orderId;


    private Long userId;


    private Long productId;


    private Double amount;
}
