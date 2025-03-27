package com.pwr.psiw.orderhistoryservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "orderHistorys", indexes = {
        @Index(name = "idx_orderhistory_order_id", columnList = "order_id")
})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Model reprezentujący historię zamówienia w systemie")
public class OrderHistory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    @Schema(description = "Unikalny identyfikator historii zamówienia", example = "1")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    @Schema(description = "Imię i nazwisko klienta, który złożył zamówienie", example = "Jan Kowalski")
    private String customerName;

    @Column(name = "delivery_status", nullable = false)
    @Schema(description = "Aktualny status dostawy zamówienia", example = "DELIVERED")
    private String deliveryStatus;

    @Column(name = "product_name", nullable = false)
    @Schema(description = "Nazwa produktu, który został zamówiony", example = "Laptop Lenovo ThinkPad X1")
    private String productName;

    @Column(name = "total_price", nullable = false)
    @Schema(description = "Całkowita cena zamówienia", example = "2999.99")
    private BigDecimal totalPrice;

}
