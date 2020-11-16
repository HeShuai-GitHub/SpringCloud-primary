package com.rest.consumerrest.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.rest.consumerrest.exception.FallbackException;
import com.rest.consumerrest.exception.FlowBlockException;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springCloud
 * @description: Bean配置类
 * @author: hs
 * @create: 2020-10-10 10:48
 **/
@Configuration
public class BeanConfig {


    /**
     *
     * @SentinelRestTemplate: 只需要在RestTempate 上配置上此注解，就自动对RestTempalte上所有服务进行管理
     *         资源名：
     *              httpmethod:schema://host:port/path：协议、主机、端口和路径
     *              httpmethod:schema://host:port：协议、主机和端口
     *          blockHandler：      限流方法
     *          blockHandlerClass：限流类
     *          fallback:          异常降级方法
     *          fallbackClass：    异常降级类
     *
     */
    @SentinelRestTemplate(fallback = "handleFallback",fallbackClass = FallbackException.class,
                            blockHandler = "handleBlock",blockHandlerClass = FlowBlockException.class)
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
