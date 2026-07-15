package com.rishit.payment_service.PAYMENT_SERVICE.repository;

import com.rishit.payment_service.PAYMENT_SERVICE.entity.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedEventRepository
        extends JpaRepository<ProcessedEvent,String> {
}
