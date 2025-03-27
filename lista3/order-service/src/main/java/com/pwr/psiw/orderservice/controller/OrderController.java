package com.pwr.psiw.orderservice.controller;

import com.pwr.psiw.orderservice.model.Order;
import com.pwr.psiw.orderservice.service.OrderService;
import com.pwr.psiw.orderservice.utils.DeliveryStatus;
import com.pwr.psiw.orderservice.utils.requests.OrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Create a new order",
            description = "Creates a new order based on the provided order details, including customer name, delivery, and order items.",
            tags = {"Orders"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully created",
                    content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Data not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(
            @Parameter(description = "Order request containing customer details, order items, and delivery information")
            @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @Operation(
            summary = "Update an existing order status",
            description = "Updates the delivery status of an order based on its ID.",
            tags = {"Orders"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order status successfully updated",
                    content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update-order")
    public ResponseEntity<Order> updateOrder(
            @Parameter(description = "ID of the order to update") @RequestParam Long orderId,
            @Parameter(description = "New delivery status") @RequestParam DeliveryStatus newStatus) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, newStatus));
    }
}
