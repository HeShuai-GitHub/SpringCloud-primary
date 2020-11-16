package com.eureka.client.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

/**
 * @program: springCloud
 * @description: 测试消费者控制层
 * @create: 2020-10-10 09:32
 **/
@RestController
@RequestMapping("consumer")
@RefreshScope // 开启动态配置
public class TestController {

    private final Logger logger= LoggerFactory.getLogger(TestController.class);
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;
    @Value("${name}")
    private String name;

    @Autowired
    private EurekaConsumerFeign eurekaConsumerFeign;

    @GetMapping("getFeign")
    public String getFeign(String name){
       return eurekaConsumerFeign.getFeign(name);
    }

    @GetMapping("getName")
    public String getName(){
        return name;
    }

    @GetMapping("getRibbon")
    public String getRibbon(String name){
        ResponseEntity<String> entity=restTemplate.getForEntity("http://eureka-provider/provider/get/"+name,String.class,"NAME");
        return "entity.getStatusCode()："+entity.getStatusCode()
                +"\n entity.getHeaders()："+entity.getHeaders()
                +"\n entity.getBody()："+entity.getBody();
    }


    /**
     * 调用eureka元数据的方法获取客户端url并调用
     * @param name
     * @return
     */
    @GetMapping("get")
    public String get(String name){
        logger.debug("进入TestController方法");
        List<ServiceInstance> instances=discoveryClient.getInstances("eureka-provider");
        if(instances.size()>0){
            ServiceInstance serviceInstance=instances.get(0);
            ResponseEntity<String> entity=restTemplate.getForEntity(serviceInstance.getUri().toString()+"/provider/get/"+name,String.class,"NAME");
            return "entity.getStatusCode()："+entity.getStatusCode()
                    +"\n entity.getHeaders()："+entity.getHeaders()
                    +"\n entity.getBody()："+entity.getBody();
        }else {
            return "暂无可提供的服务";
        }

    }

    /**
     * 调用eureka元数据的方法获取客户端url并调用
     * @return
     */
    @GetMapping("detail")
    public String detail(){
        /**
         * 获取注册中心服务名集合
         */
        List<String> services=discoveryClient.getServices();
        for (String service:services) {
            System.out.println(service);
        }
        System.out.println("*********************");
        System.out.println("*********************");
        List<ServiceInstance> instances=discoveryClient.getInstances("eureka-provider");
        for (ServiceInstance instance:instances
             ) {
            /**
             * instance.getHost()：10.159.82.116
             * 获取该服务ip
             */
            System.out.println("instance.getHost()："+instance.getHost());
            /**
             * instance.getScheme()：http
             * 获取该服务协议
             */
            System.out.println("instance.getScheme()："+instance.getScheme());
            /**
             * instance.getServiceId()：EUREKA-CONSUMER
             * 获取该服务名称
             */
            System.out.println("instance.getServiceId()："+instance.getServiceId());
            /**
             * instance.getMetadata()：{management.port=9002}
             * 获取该服务元数据
             */
            System.out.println("instance.getMetadata()："+instance.getMetadata());
            /**
             * instance.getPort()：9002
             * 获取该服务端口号
             */
            System.out.println("instance.getPort()："+instance.getPort());
            /**
             * instance.getUri()：http://10.159.82.116:9002
             * 获取该服务url路径
             */
            System.out.println("instance.getUri()："+instance.getUri());
        }
        return null;
    }


}
