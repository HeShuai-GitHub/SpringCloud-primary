package com.consul.provider.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springCloud
 * @description: 测试控制层
 * @author: hs
 * @create: 2020-10-09 22:07
 **/
@RestController
@RequestMapping("provider")
public class TestServiceImpl {

    private final Logger logger= LoggerFactory.getLogger(TestServiceImpl.class);

    @GetMapping("get/{name}")
    public String get(@PathVariable("name") String name){
        logger.debug("进入TestServiceImpl方法");
        return "provider 端口号：8090 name : "+name;
    }
}
