package com.rishit.order_service.ORDER_SERVICE.repository;

import com.rishit.order_service.ORDER_SERVICE.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
