package com.pwr.psiw.orderhistoryservice.exeption.handler;

import java.time.LocalDateTime;

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime timestamp
) {
}
