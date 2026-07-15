package com.rishit.inventory_service.INVENTORY_SERVICE.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequest {

    private Long productId;

    private Integer quantity;
}
