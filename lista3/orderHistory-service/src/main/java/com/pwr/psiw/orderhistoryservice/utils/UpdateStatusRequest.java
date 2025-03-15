package com.pwr.psiw.orderhistoryservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusRequest {
    private Long orderId;
    private String status;
}
