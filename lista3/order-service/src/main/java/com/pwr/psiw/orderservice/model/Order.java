package com.pwr.psiw.orderservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Schema(description = "Represents a customer's order, containing order items and delivery details.")
@Table(name = "orders", indexes = {
        @Index(name = "idx_order_id_unq", columnList = "id", unique = true)
})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Order {

    @Schema(description = "Unique identifier for the order", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Name of the customer who placed the order", example = "John Doe")
    @Column(name = "customer_name", nullable = false)
    @NotEmpty
    private String customerName;

    @Schema(description = "Delivery details associated with the order")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    @Schema(description = "List of order items included in the order")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<OrderItem> items;
}
