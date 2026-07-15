package com.rishit.inventory_service.INVENTORY_SERVICE.controller;

import com.rishit.inventory_service.INVENTORY_SERVICE.dto.InventoryRequest;
import com.rishit.inventory_service.INVENTORY_SERVICE.dto.InventoryResponse;
import com.rishit.inventory_service.INVENTORY_SERVICE.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @PostMapping
    public InventoryResponse addInventory(
            @RequestBody InventoryRequest request
    ) {

        return inventoryService.addInventory(request);
    }


    @GetMapping("/{productId}")
    public InventoryResponse getInventory(
            @PathVariable Long productId
    ) {

        return inventoryService.getInventory(productId);
    }


    @PutMapping("/{productId}")
    public InventoryResponse updateInventory(
            @PathVariable Long productId,
            @RequestBody InventoryRequest request
    ) {

        return inventoryService.updateInventory(
                productId,
                request
        );
    }
}
