package com.rishit.order_service.ORDER_SERVICE.repository;

import com.rishit.order_service.ORDER_SERVICE.entity.OutboxEvent;
import com.rishit.order_service.ORDER_SERVICE.entity.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxRepository
        extends JpaRepository<OutboxEvent, Long> {


    List<OutboxEvent> findByStatus(
            OutboxStatus status
    );

}