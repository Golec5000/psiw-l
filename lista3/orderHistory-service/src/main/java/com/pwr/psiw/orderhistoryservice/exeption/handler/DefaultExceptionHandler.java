package com.pwr.psiw.orderhistoryservice.exeption.handler;

import com.pwr.psiw.orderhistoryservice.exeption.custome.OrderHistoryNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(OrderHistoryNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundExceptions(Exception e, HttpServletRequest request) {
        return createResponseEntity(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e, HttpServletRequest request) {
        return createResponseEntity(e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                .orElse("Validation error");
        return createResponseEntity(new Exception(errorMessage), request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiError> createResponseEntity(Exception e, HttpServletRequest request, HttpStatus status) {
        ApiError apiError = new ApiError(request.getRequestURI(), e.getMessage(), status.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiError, status);
    }

}
