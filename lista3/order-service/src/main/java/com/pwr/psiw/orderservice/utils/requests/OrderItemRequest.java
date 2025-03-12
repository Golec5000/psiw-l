package com.pwr.psiw.orderservice.utils.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Request object representing an item within an order.")
@Data
@Builder
public class OrderItemRequest {

    @Schema(description = "Unique identifier of the product being ordered", example = "1")
    private Long productId;

    @Schema(description = "Quantity of the product in the order", example = "2")
    private Integer quantity;
}
