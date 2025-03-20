package com.pwr.psiw.orderhistoryservice.service;

import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import com.pwr.psiw.orderhistoryservice.utils.PageResponse;
import org.springframework.hateoas.EntityModel;

public interface OrderHistoryService {
    PageResponse<EntityModel<OrderHistory>> findAll(int pageNo, int pageSize);

    OrderHistory findById(Long orderId);

    OrderHistory save(OrderHistory orderHistory);

    OrderHistory update(Long orderId, String status);
}
