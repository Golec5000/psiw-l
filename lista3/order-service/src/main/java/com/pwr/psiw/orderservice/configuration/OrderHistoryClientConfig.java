package com.pwr.psiw.orderservice.configuration;

import com.pwr.psiw.orderservice.client.ApiClient;
import com.pwr.psiw.orderservice.client.api.OrderHistoryControllerApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderHistoryClientConfig {

    @Bean
    public OrderHistoryControllerApi orderHistoryControllerApi() {
        return new OrderHistoryControllerApi(new ApiClient().setBasePath("http://localhost:8081"));
    }

}
