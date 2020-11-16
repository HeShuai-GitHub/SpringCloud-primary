package com.appolo.demo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @EnableApolloConfig: 在启动类上添加此依赖，启动Apollo
 */
@SpringBootApplication
@RestController
@RequestMapping
@EnableApolloConfig
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Value("${name}")
    private String name;

    @GetMapping("getName")
    public String getName(){
        return name;
    }
}
