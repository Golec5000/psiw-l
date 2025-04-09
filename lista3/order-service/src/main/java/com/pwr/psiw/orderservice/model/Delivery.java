package com.pwr.psiw.orderservice.model;

import com.pwr.psiw.orderservice.utils.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Represents the delivery details of an order.")
@Table(name = "delivers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Delivery {

    @Schema(description = "Unique identifier for the delivery", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Name of the courier handling the delivery", example = "DHL")
    @Column(name = "courier_name", nullable = false)
    @NotNull
    private String courierName;

    @Schema(description = "Current status of the delivery", example = "PICKED_UP")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
