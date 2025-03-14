package com.pwr.psiw.orderhistoryservice.repository;

import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    Page<OrderHistory> findAll(Pageable pageable);
}
