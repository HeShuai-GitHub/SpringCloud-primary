package com.feign.consumerfeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign 接口调用微服务
 *  @FeignClient value=服务名  fallback=sentinel 熔断降级实现类
 */
@FeignClient(value = "eureka-provider",fallback = EurekaConsumerFeignFallback.class)
public interface EurekaConsumerFeign {

    /**
     * getMapping
     * feign 不支持GET方法传输POJO 可以转换成json，再换成map
     */
    @GetMapping("/provider/get/{name}")
    String getFeign(@PathVariable("name") String name);
}
