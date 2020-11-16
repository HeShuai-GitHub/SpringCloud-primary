package com.consumser.consumserrest.controller;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: springCloud
 * @description: 测试消费者控制层
 * @author: hs
 * @create: 2020-10-10 09:32
 **/
@RestController
@RequestMapping("consumer")
/**
 * DefaultProperties：指定接口中的公共的熔断设置
 *          也可以为个别服务特别指定熔断设置
 */
@DefaultProperties(defaultFallback = "defaultFallback")
public class TestController {

    private final Logger logger= LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RestTemplate restTemplate;

//    @HystrixCommand(fallbackMethod = "testFallback")
    @HystrixCommand
    @GetMapping("getRibbon")
    public String getRibbon(String name){
        if (!name.equals("1")){
            throw new RuntimeException();
        }
        ResponseEntity<String> entity=restTemplate.getForEntity("http://eureka-provider/provider/get/"+name,String.class,"NAME");
        return "entity.getStatusCode()："+entity.getStatusCode()
                +"\n entity.getHeaders()："+entity.getHeaders()
                +"\n entity.getBody()："+entity.getBody();
    }


    /**
     *
     * 设置统一降级方法，返回值相同，参数为空
     *
     */
    public String defaultFallback(){
        return "触发统一降级方法";
    }

    /**
     *
     * Hystrix降级方法，如需要为getRibbon方法设置降级方法，必须返回值和入餐保持一致
     *
     */
    public String testFallback(String name){
        return "触发降级方法";
    }


}
