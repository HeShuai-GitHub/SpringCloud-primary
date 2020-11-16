package com.restemplate.consumercommon;

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


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
