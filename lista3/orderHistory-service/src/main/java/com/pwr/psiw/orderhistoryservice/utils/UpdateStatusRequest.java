package com.pwr.psiw.orderhistoryservice.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateStatusRequest(

        @NotNull
        @Positive
        @Schema(description = "ID for looking Order History obj")
        Long orderId,

        @NotEmpty
        @Schema(description = "Status of the order")
        String status
) {
}