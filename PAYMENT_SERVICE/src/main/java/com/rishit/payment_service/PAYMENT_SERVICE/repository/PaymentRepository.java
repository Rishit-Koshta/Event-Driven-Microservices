package com.rishit.payment_service.PAYMENT_SERVICE.repository;

import com.rishit.payment_service.PAYMENT_SERVICE.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
