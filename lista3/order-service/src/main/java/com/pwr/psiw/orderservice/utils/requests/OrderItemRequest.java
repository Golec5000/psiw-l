package com.pwr.psiw.orderservice.utils.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Request object representing an item within an order.")
public record OrderItemRequest(
        @Schema(description = "Unique identifier of the product being ordered", example = "1")
        @NotNull
        @Positive
        Long productId,

        @Schema(description = "Quantity of the product in the order", example = "2")
        @NotNull
        @Positive(message = "Quantity must be greater than zero")
        Integer quantity
) {
}