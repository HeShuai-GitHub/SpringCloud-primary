package com.gateway.apigateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: springCloud
 * @description: redis的key解析器
 * @create: 2020-11-11 23:34
 **/
@Configuration
public class KeyResolverConfiguartion {

    /**
     * 可以定义根据Path、Param、Ip来进行限流
     * 根据Path限流
     */
/*    @Bean
    public KeyResolver keyResolver(){
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getPath().toString());
            }
        };
    }*/

    /**
     * 根据Path限流
     */
/*    @Bean
    public KeyResolver keyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
    }*/

    /**
     * 根据Param限流
     */
/*    @Bean
    public KeyResolver keyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("token"));
    }*/

    /**
     * 根据ip限流
     */
    @Bean
    public KeyResolver keyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

}
