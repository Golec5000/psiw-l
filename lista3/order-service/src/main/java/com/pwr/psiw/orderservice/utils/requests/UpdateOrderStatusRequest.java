package com.pwr.psiw.orderservice.utils.requests;

import com.pwr.psiw.orderservice.utils.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request object for updating the status of an order.")
public record UpdateOrderStatusRequest(
        @Schema(description = "ID for looking up the Order History object", example = "123")
        @NotNull
        Long orderId,

        @Schema(description = "Status of the order", example = "DELIVERED")
        @NotNull
        @Enumerated(EnumType.STRING)
        DeliveryStatus status
) {
}