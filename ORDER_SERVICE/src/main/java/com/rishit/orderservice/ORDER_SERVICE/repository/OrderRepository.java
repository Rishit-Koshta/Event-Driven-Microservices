package com.rishit.orderservice.ORDER_SERVICE.repository;

import com.rishit.orderservice.ORDER_SERVICE.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
