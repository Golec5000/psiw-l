package com.pwr.psiw.orderservice.service;

import com.pwr.psiw.orderservice.model.Order;
import com.pwr.psiw.orderservice.utils.requests.OrderRequest;
import com.pwr.psiw.orderservice.utils.requests.UpdateOrderStatusRequest;

public interface OrderService {

    Order createOrder(OrderRequest orderRequest);

    Order updateOrder(UpdateOrderStatusRequest updateOrderStatusRequest);

}
