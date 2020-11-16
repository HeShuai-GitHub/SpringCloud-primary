package com.gateway.apigateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @program: springCloud
 * @description: 集成sentinel分流配置类
 * @create: 2020-11-13 10:57
 **/
@Configuration
public class SentinelConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public SentinelConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                 ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 注册异常处理器
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * 将sentinel过滤器加入到全局过滤器链中
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * @PostConstruct：初始化执行此方法
     */
    @PostConstruct
    public void doInit() {
        initGatewayRules();
        initBlockHandler();
        initCustomizeApis();
    }

    /**
     * 初始化限流规则
     */
    private void initGatewayRules() {
        /*GatewayFlowRule：网关限流规则，针对 API Gateway 的场景定制的限流规则，
        可以针对不同 route 或自定义的 API 分组进行限流，
        支持针对请求中的参数、Header、来源 IP 等进行定制化的限流。
        */
        Set<GatewayFlowRule> rules = new HashSet<>();
        /*设置限流规则
        resource: 资源名称，这里为路由router的ID
        resourceMode: 路由模式
        count: QPS即每秒钟允许的调用次数
        intervalSec: 每隔多少时间统计一次汇总数据，统计时间窗口，单位是秒，默认是 1 秒。
        */
//        rules.add(new GatewayFlowRule("eureka-consumer")
//                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_ROUTE_ID)
//                .setCount(1).setIntervalSec(1));
        rules.add(new GatewayFlowRule("eureka-consumer")
                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
                .setCount(1).setIntervalSec(1));
        rules.add(new GatewayFlowRule("eureka-provider")
                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
                .setCount(1).setIntervalSec(1));
        /**
         * 配置gateway rule 规则
         */
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 自定义API限流分组
     *      1.定义分组
     *      2.对小组配置限流规则
     */
    private void initCustomizeApis(){
        Set<ApiDefinition> definitions = new HashSet<>();
        // 匹配/eureka-consumer/ 开头的所有url
        ApiDefinition apiDefinition1 = new ApiDefinition("eureka-consumer")
                .setPredicateItems(new HashSet<ApiPredicateItem>(){{
                    add(new ApiPathPredicateItem().setPattern("/eureka-consumer/**")
                    .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));

                }});
        // 完全匹配/eureka-provider/provider/get/1
        ApiDefinition apiDefinition = new ApiDefinition("eureka-provider")
                .setPredicateItems(new HashSet<ApiPredicateItem>(){{
                        add(new ApiPathPredicateItem().setPattern("/eureka-provider/provider/get/1"));
                    }});
        definitions.add(apiDefinition);
        definitions.add(apiDefinition1);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

    /**
     * 自定义限流异常处理
     * ResultSupport 为自定义的消息封装类，代码略
     */
//    private void initBlockHandler() {
//        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
//            @Override
//            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange,
//                                                      Throwable throwable) {
//                Map<String,String> map = new HashMap<>();
//                map.put("code","1400");
//                map.put("message","Sentinel block");
//                return ServerResponse.status(HttpStatus.OK)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .body(BodyInserters.fromObject(map));
//            }
//        });
//    }

    /**
     * 限流提示信息
     */
    private void initBlockHandler() {
        GatewayCallbackManager.setBlockHandler((serverWebExchange,throwable)->{
            Map<String,String> map = new HashMap<>();
            map.put("code","1400");
            map.put("message","Sentinel block");
            return ServerResponse.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromObject(map));
        });
    }
}
