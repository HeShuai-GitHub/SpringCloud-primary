package com.cloud.eurekaserverb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerbApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerbApplication.class, args);
    }

}
