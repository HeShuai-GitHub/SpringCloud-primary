package com.feign.consumerfeign.controller;

import com.feign.consumerfeign.feign.EurekaConsumerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springCloud
 * @description:
 * @author: hs
 * @create: 2020-11-09 15:11
 **/
@RestController
@RequestMapping("consumer")
public class TestController {

    @Autowired
    private EurekaConsumerFeign eurekaConsumerFeign;

    @GetMapping("getFeign")
    public String getFeign(String name){
        return eurekaConsumerFeign.getFeign(name);
    }
}
