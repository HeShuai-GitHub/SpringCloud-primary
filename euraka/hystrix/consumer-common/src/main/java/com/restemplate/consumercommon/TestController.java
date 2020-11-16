package com.restemplate.consumercommon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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

    @GetMapping("getMessage")
    public String getFeign(String name){
       return "This get Message, Date:"+new Date().toString();
    }


    @GetMapping("getTest")
    public String getRibbon(){
        ResponseEntity<String> entity=restTemplate.getForEntity("http://127.0.0.1:9011/provider/get/110",String.class,"NAME");
        return "entity.getStatusCode()："+entity.getStatusCode()
                +"\n entity.getHeaders()："+entity.getHeaders()
                +"\n entity.getBody()："+entity.getBody();
    }



}
