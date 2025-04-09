package com.pwr.psiw.orderservice.service;

import com.pwr.psiw.orderservice.client.api.CreatDataApi;
import com.pwr.psiw.orderservice.client.api.UpdateDataApi;
import com.pwr.psiw.orderservice.client.model.OrderHistory;
import com.pwr.psiw.orderservice.client.model.UpdateStatusRequest;
import com.pwr.psiw.orderservice.exeptions.custom.OrderHistoryServiceException;
import com.pwr.psiw.orderservice.exeptions.custom.OrderNotFoundException;
import com.pwr.psiw.orderservice.exeptions.custom.ProductNotFoundException;
import com.pwr.psiw.orderservice.model.Delivery;
import com.pwr.psiw.orderservice.model.Order;
import com.pwr.psiw.orderservice.model.OrderItem;
import com.pwr.psiw.orderservice.model.Product;
import com.pwr.psiw.orderservice.repository.OrderRepository;
import com.pwr.psiw.orderservice.repository.ProductRepository;
import com.pwr.psiw.orderservice.utils.DeliveryStatus;
import com.pwr.psiw.orderservice.utils.requests.OrderItemRequest;
import com.pwr.psiw.orderservice.utils.requests.OrderRequest;
import com.pwr.psiw.orderservice.utils.requests.UpdateOrderStatusRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CreatDataApi creatDataApi;
    private final UpdateDataApi updateDataApi;


    @Override
    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerName(orderRequest.customerName());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderRequest.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.quantity());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);

        Delivery delivery = new Delivery();
        delivery.setCourierName(orderRequest.courierName());
        delivery.setDeliveryStatus(DeliveryStatus.CREATED);
        order.setDelivery(delivery);

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.customerName(orderRequest.customerName());
        orderHistory.deliveryStatus(DeliveryStatus.CREATED.name());
        orderHistory.setProductName(
                orderItems.stream()
                        .map(item -> item.getProduct().getName() + " (x" + item.getQuantity() + ")")
                        .reduce((s1, s2) -> s1 + ", " + s2)
                        .orElse("")
        );
        orderHistory.setTotalPrice(
                orderItems.stream()
                        .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
        );

        try {
            creatDataApi.saveOrderHistory(orderHistory);
        } catch (RestClientException e) {
            throw new OrderHistoryServiceException("Failed to sync with OrderHistoryService" + e.getMessage());
        }

        return orderRepository.save(order);
    }


    @Override
    @Transactional
    public Order updateOrder(UpdateOrderStatusRequest updateOrderStatusRequest) {
        Order order = orderRepository.findById(updateOrderStatusRequest.orderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        DeliveryStatus newStatus = updateOrderStatusRequest.status();
        order.getDelivery().setDeliveryStatus(newStatus);

        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setOrderId(order.getId());
        request.setStatus(newStatus.name());

        try {
            updateDataApi.updateOrderStatus(request);
        } catch (RestClientException e) {
            throw new OrderHistoryServiceException("Failed to sync with OrderHistoryService: " + e.getMessage());
        }

        return orderRepository.save(order);
    }

}
