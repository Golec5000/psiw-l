package com.pwr.psiw.orderhistoryservice.service;

import com.pwr.psiw.orderhistoryservice.controller.OrderHistoryController;
import com.pwr.psiw.orderhistoryservice.exeption.custome.OrderHistoryNotFoundException;
import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import com.pwr.psiw.orderhistoryservice.repository.OrderHistoryRepository;
import com.pwr.psiw.orderhistoryservice.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImp implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public PageResponse<EntityModel<OrderHistory>> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<OrderHistory> orderHistoryPage = orderHistoryRepository.findAll(pageable);

        // Tworzenie linków HATEOAS dla każdej pozycji (teraz do OrderHistoryController)
        List<EntityModel<OrderHistory>> orderHistoryResources = orderHistoryPage.getContent().stream()
                .map(order -> EntityModel.of(order,
                        linkTo(methodOn(OrderHistoryController.class).getOrderById(order.getId())).withSelfRel()))
                .collect(Collectors.toList());

        // Tworzenie linków paginacji
        Link selfLink = linkTo(methodOn(OrderHistoryController.class).getAllOrders(pageNo, pageSize)).withSelfRel();
        Link nextLink = pageNo < orderHistoryPage.getTotalPages() - 1
                ? linkTo(methodOn(OrderHistoryController.class).getAllOrders(pageNo + 1, pageSize)).withRel("next")
                : null;
        Link prevLink = pageNo > 0
                ? linkTo(methodOn(OrderHistoryController.class).getAllOrders(pageNo - 1, pageSize)).withRel("prev")
                : null;

        return new PageResponse<>(
                orderHistoryPage.getNumber(),
                orderHistoryPage.getSize(),
                orderHistoryPage.getTotalPages(),
                orderHistoryResources,
                orderHistoryPage.isLast(),
                orderHistoryPage.hasNext(),
                Stream.of(selfLink, nextLink, prevLink)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
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

