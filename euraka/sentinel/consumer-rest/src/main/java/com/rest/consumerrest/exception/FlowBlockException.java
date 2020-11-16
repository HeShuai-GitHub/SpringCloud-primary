package com.rest.consumerrest.exception;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @program: springCloud
 * @description: 限流触发
 * @author: hs
 * @create: 2020-11-09 11:53
 **/
public class FlowBlockException {
    /**
     *  熔断降级方法：
     *      static 静态
     *      SentinelClientHttpResponse 返回值
     *      HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception:参数
     */
    public static SentinelClientHttpResponse handleBlock(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception) {
        return new SentinelClientHttpResponse(JSON.toJSONString("限流熔断降级方法"));
    }
}
