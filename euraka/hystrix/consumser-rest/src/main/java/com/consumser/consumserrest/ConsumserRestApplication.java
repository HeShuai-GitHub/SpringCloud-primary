package com.consumser.consumserrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//启用hystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ConsumserRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumserRestApplication.class, args);
    }

}
