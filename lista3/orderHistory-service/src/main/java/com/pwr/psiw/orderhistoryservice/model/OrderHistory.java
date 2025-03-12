package com.pwr.psiw.orderhistoryservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "orderHistorys", indexes = {
        @Index(name = "idx_orderhistory_order_id", columnList = "order_id")
})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;


}
