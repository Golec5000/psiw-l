package com.pwr.psiw.orderhistoryservice.exeption.custome;

public class OrderHistoryNotFoundException extends RuntimeException {
    public OrderHistoryNotFoundException(String message) {
        super(message);
    }
}
