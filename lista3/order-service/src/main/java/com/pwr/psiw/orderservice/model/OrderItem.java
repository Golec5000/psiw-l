package com.pwr.psiw.orderservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Represents an item within an order, linked to a product.")
@Table(name = "order_items")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItem {

    @Schema(description = "Unique identifier for the order item", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Quantity of the product ordered", example = "2")
    private int quantity;

    @Schema(description = "The order associated with this item")
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonBackReference
    private Order order;

    @Schema(description = "The product that has been ordered")
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonBackReference
    private Product product;
}
