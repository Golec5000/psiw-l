package com.pwr.psiw.orderservice.configuration;

import com.pwr.psiw.orderservice.client.ApiClient;
import com.pwr.psiw.orderservice.client.api.CreatDataApi;
import com.pwr.psiw.orderservice.client.api.UpdateDataApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderHistoryClientConfig {

    @Bean
    public CreatDataApi creatDataApi() {
        return new CreatDataApi(new ApiClient().setBasePath("http://localhost:8081"));
    }

    @Bean
    public UpdateDataApi updateDataApi() {
        return new UpdateDataApi(new ApiClient().setBasePath("http://localhost:8081"));
    }

}