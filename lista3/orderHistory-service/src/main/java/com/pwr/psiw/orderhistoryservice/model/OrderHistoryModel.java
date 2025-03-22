package com.pwr.psiw.orderhistoryservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderHistoryModel extends RepresentationModel<OrderHistoryModel> {
    private Long id;
    private String customerName;
    private String deliveryStatus;
    private String productName;
    private BigDecimal totalPrice;

    public OrderHistoryModel(OrderHistory entity) {
        this.id = entity.getId();
        this.customerName = entity.getCustomerName();
        this.deliveryStatus = entity.getDeliveryStatus();
        this.productName = entity.getProductName();
        this.totalPrice = entity.getTotalPrice();
    }
}