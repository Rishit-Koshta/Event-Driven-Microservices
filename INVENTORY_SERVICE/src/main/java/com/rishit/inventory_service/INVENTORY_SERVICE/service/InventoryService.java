package com.rishit.inventory_service.INVENTORY_SERVICE.service;

import com.rishit.inventory_service.INVENTORY_SERVICE.dto.InventoryRequest;
import com.rishit.inventory_service.INVENTORY_SERVICE.dto.InventoryResponse;
import com.rishit.inventory_service.INVENTORY_SERVICE.entity.Inventory;
import com.rishit.inventory_service.INVENTORY_SERVICE.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    // Existing reserveStock() remains here


    public InventoryResponse addInventory(
            InventoryRequest request
    ) {

        if (repository.findByProductId(request.getProductId()).isPresent()) {
            throw new RuntimeException("Inventory already exists for this product.");
        }

        Inventory inventory = Inventory.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();

        repository.save(inventory);

        return InventoryResponse.builder()
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .build();
    }


    public InventoryResponse getInventory(
            Long productId
    ) {

        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow();

        return InventoryResponse.builder()
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .build();
    }


    public InventoryResponse updateInventory(
            Long productId,
            InventoryRequest request
    ) {

        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow();

        inventory.setQuantity(request.getQuantity());

        repository.save(inventory);

        return InventoryResponse.builder()
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .build();
    }
}
