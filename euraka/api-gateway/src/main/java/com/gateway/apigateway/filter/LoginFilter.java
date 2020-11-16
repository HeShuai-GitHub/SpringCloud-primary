package com.gateway.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: springCloud
 * @description: 测试 Gateway中GlobalFilter
 * @create: 2020-11-11 16:00
 **/
@Component
public class LoginFilter implements GlobalFilter, Ordered {

    /**
     * filter:在这个中处理过滤器的逻辑内容
     *      ServerWebExchange：类似于zuul中RequestContext，可以获取上下文对象
     *      GatewayFilterChain：filter 链
     *
     *  以下逻辑实现 判断token是否存在，若不存在则结束路由，返回401
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(StringUtils.isEmpty(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * filter 执行顺序，越小执行级别越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
