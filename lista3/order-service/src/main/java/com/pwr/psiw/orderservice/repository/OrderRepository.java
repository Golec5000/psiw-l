package com.pwr.psiw.orderservice.repository;

import com.pwr.psiw.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
