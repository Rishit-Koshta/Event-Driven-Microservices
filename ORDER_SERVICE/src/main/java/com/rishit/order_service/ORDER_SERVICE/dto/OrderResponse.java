package com.rishit.order_service.ORDER_SERVICE.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {

    private Long orderId;
    private Double amount;
    private String status;

}
