package com.pwr.psiw.orderhistoryservice.service;

import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import com.pwr.psiw.orderhistoryservice.utils.PageResponse;

public interface OrderHistoryService {
    PageResponse<OrderHistory> findAll(int pageNo, int pageSize);

    OrderHistory findById(Long orderId);

    OrderHistory save(OrderHistory orderHistory);

    OrderHistory update(Long orderId, String status);
}
