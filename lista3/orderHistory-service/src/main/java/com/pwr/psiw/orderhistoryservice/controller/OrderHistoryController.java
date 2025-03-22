package com.pwr.psiw.orderhistoryservice.controller;

import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import com.pwr.psiw.orderhistoryservice.model.OrderHistoryModel;
import com.pwr.psiw.orderhistoryservice.service.OrderHistoryService;
import com.pwr.psiw.orderhistoryservice.utils.PageResponse;
import com.pwr.psiw.orderhistoryservice.utils.UpdateStatusRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-history")
@RequiredArgsConstructor
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @Operation(summary = "Get all order history entries with pagination and HATEOAS links")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order history entries found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-all")
    public ResponseEntity<PageResponse<OrderHistoryModel>> getAllOrders(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(orderHistoryService.findAll(pageNo, pageSize));
    }

    @Operation(summary = "Get order history entry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order history entry found"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-by-id")
    public ResponseEntity<OrderHistory> getOrderById(@RequestParam(value = "orderId") Long orderId) {
        return ResponseEntity.ok(orderHistoryService.findById(orderId));
    }

    @Operation(summary = "Create a new order history entry",
            description = "Stores a new order record in the history service after an order is created in OrderService.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order history entry created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/save-order-history")
    public ResponseEntity<OrderHistory> saveOrderHistory(@RequestBody OrderHistory orderHistory) {
        return ResponseEntity.ok(orderHistoryService.save(orderHistory));
    }

    @Operation(summary = "Update order history status",
            description = "Updates the status of an existing order in OrderHistoryService.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order status updated"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update-order-status")
    public ResponseEntity<OrderHistory> updateOrderStatus(@RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(orderHistoryService.update(request.getOrderId(), request.getStatus()));
    }



}
