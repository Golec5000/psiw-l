package com.pwr.psiw.orderservice.utils.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Schema(description = "Request object for creating a new order.")
@Data
@Builder
public class OrderRequest {

    @Schema(description = "Full name of the customer placing the order", example = "John Doe")
    private String customerName;

    @Schema(description = "List of items included in the order")
    private List<OrderItemRequest> items;

    @Schema(description = "Name of the courier service handling the delivery", example = "DHL")
    private String courierName;
}
