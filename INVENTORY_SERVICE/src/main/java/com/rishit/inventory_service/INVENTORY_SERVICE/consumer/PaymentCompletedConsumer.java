package com.rishit.inventory_service.INVENTORY_SERVICE.consumer;
import com.rishit.common.event.PaymentCompletedEvent;
import com.rishit.inventory_service.INVENTORY_SERVICE.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentCompletedConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(
            topics = "payment-completed",
            groupId = "inventory-group"
    )
    public void consume(PaymentCompletedEvent event){

        boolean reserved =
                inventoryService.reserveStock(
                        event.getProductId()
                );

        if(reserved){
            System.out.println(
                    "Inventory reserved for order "
                            + event.getOrderId()
            );
        }else{
            System.out.println(
                    "Inventory unavailable for order "
                            + event.getOrderId()
            );
        }
    }
}