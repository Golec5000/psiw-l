package com.pwr.psiw.orderservice.utils.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Request object for creating a new order.")
public record OrderRequest(
        @Schema(description = "Full name of the customer placing the order", example = "John Doe")
        @NotEmpty
        String customerName,

        @Schema(description = "List of items included in the order")
        @NotNull
        @Valid
        List<OrderItemRequest> items,

        @Schema(description = "Name of the courier service handling the delivery", example = "DHL")
        @NotEmpty
        String courierName
) {
}