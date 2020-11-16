package com.eureka.client.consumer.controller;

import org.springframework.stereotype.Component;

/**
 * @program: springCloud
 * @description: Fallback 降级方法,需要将@Component加入ioc容器
 * @author: hs
 * @create: 2020-11-01 10:28
 **/
@Component
public class EurekaConsumerFeignFallback implements EurekaConsumerFeign{
    @Override
    public String getFeign(String name) {
        return "触发Feign 降级方法";
    }
}
