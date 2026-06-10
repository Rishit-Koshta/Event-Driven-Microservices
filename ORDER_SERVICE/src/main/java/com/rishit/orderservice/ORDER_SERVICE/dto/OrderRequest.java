package com.rishit.orderservice.ORDER_SERVICE.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class OrderRequest {

    @NotNull
    private Long userId;
    @Positive
    private Double amount;
}
