package com.pwr.psiw.orderhistoryservice.service;

import com.pwr.psiw.orderhistoryservice.exeption.custome.OrderHistoryNotFoundException;
import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import com.pwr.psiw.orderhistoryservice.repository.OrderHistoryRepository;
import com.pwr.psiw.orderhistoryservice.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImp implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;


    @Override
    public PageResponse<OrderHistory> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<OrderHistory> orderHistoryPage = orderHistoryRepository.findAll(pageable);
        return new PageResponse<>(
                orderHistoryPage.getNumber(),
                orderHistoryPage.getSize(),
                orderHistoryPage.getTotalPages(),
                orderHistoryPage.getContent(),
                orderHistoryPage.isLast(),
                orderHistoryPage.hasNext()
        );
    }

    @Override
    public OrderHistory findById(Long orderId) {
        return findOrderById(orderId);
    }

    @Override
    public OrderHistory save(OrderHistory orderHistory) {
        return orderHistoryRepository.save(orderHistory);
    }

    @Override
    public OrderHistory update(Long orderId, String status) {
        OrderHistory orderHistory = findOrderById(orderId);
        orderHistory.setDeliveryStatus(status);
        return orderHistoryRepository.save(orderHistory);
    }

    private OrderHistory findOrderById(Long orderId) {
        return orderHistoryRepository.findById(orderId)
                .orElseThrow(() -> new OrderHistoryNotFoundException("Order with id: " + orderId + " not found"));
    }
}
