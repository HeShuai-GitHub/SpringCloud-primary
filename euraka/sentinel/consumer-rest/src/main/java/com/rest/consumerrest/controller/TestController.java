package com.rest.consumerrest.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springCloud
 * @description: 测试消费者控制层
 * @author: hs
 * @create: 2020-10-10 09:32
 **/
@RestController
@RequestMapping("consumer")
public class TestController {

    private final Logger logger= LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RestTemplate restTemplate;


    /**
     *  @SentinelResource
     *      blockHandler：发生限流熔断时调用的降级方法
     *      fallback：抛出异常执行的降级方法
     */
//    @SentinelResource(value = "consumer-1",blockHandler = "testBlockHandler",fallback = "testFallback")
    @GetMapping("getMessage")
    public String getMessage(String name){
        if (!name.equals("1")){
            throw new RuntimeException();
        }
        ResponseEntity<String> entity=restTemplate.getForEntity("http://eureka-provider/provider/get/"+name,String.class,"NAME");
        return "entity.getStatusCode()："+entity.getStatusCode()
                +"\n entity.getHeaders()："+entity.getHeaders()
                +"\n entity.getBody()："+entity.getBody();
    }


    /**
     * sentinel
     *      发生异常执行的降级方法
     *      限流熔断执行的降级方法
     */
//    public String testFallback(String name){
//        return "抛出异常触发的降级方法";
//    }
//
//    public String testBlockHandler(String name){
//        return "发生限流熔断时触发的降级方法";
//    }


}
