package com.pwr.psiw.orderservice.service;

import com.pwr.psiw.orderservice.model.Order;
import com.pwr.psiw.orderservice.utils.DeliveryStatus;
import com.pwr.psiw.orderservice.utils.requests.OrderRequest;

public interface OrderService {

    public Order createOrder(OrderRequest orderRequest);

    public Order updateOrder(Long orderId, DeliveryStatus newStatus);

}
