package com.pwr.psiw.orderservice.exeptions.custom;

public class OrderHistoryServiceException extends RuntimeException {
    public OrderHistoryServiceException(String message) {
        super(message);
    }
}
