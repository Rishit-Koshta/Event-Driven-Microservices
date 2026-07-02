package com.rishit.inventory_service.INVENTORY_SERVICE.service;

import com.rishit.inventory_service.INVENTORY_SERVICE.entity.Inventory;
import com.rishit.inventory_service.INVENTORY_SERVICE.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    @Transactional
    public boolean reserveStock(Long productId){

        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if(inventory.getQuantity() <= 0){
            return false;
        }

        inventory.setQuantity(
                inventory.getQuantity() - 1
        );

        repository.save(inventory);

        return true;
    }
}
