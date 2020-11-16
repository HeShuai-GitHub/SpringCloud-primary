package com.rest.consumerrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class ConsumerRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRestApplication.class, args);
    }

}
