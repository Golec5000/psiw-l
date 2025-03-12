package com.pwr.psiw.orderservice.service;

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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Override
    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());

        // Tworzenie pozycji zamówienia
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);

        // Tworzenie dostawy
        Delivery delivery = new Delivery();
        delivery.setCourierName(orderRequest.getCourierName());
        delivery.setDeliveryStatus(DeliveryStatus.CREATED);
        order.setDelivery(delivery);

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, DeliveryStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.getDelivery().setDeliveryStatus(newStatus);
        return orderRepository.save(order);
    }
}
