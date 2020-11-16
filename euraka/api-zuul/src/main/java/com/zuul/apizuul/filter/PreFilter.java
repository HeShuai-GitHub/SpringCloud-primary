package com.zuul.apizuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springCloud
 * @description: 实现zuul网关过滤器，该类必须交由spring ioc容器进行管理
 * @create: 2020-11-09 22:19
 **/
@Component
public class PreFilter extends ZuulFilter {

    /**
     * 方法返回字符串数据，代表当前过滤器的类型。可选值有-pre, route, post, error。
     *      pre - 前置过滤器，在请求被路由前执行，通常用于处理身份认证，日志记录等；
     *      route - 在路由执行后，服务调用前被调用；
     *      error - 任意一个filter发生异常的时候执行或远程服务调用没有反馈的时候执行（超时），通常用于处理异常；
     *      post - 在route或error执行后被调用，一般用于收集服务信息，统计服务性能指标等，也可以对response结果做特殊处理。
     *
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 返回int数据，用于为同filterType的多个过滤器定制执行顺序，返回值越小，执行顺序越优先。
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter：返回boolean数据，代表当前filter是否生效。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run：具体的过滤执行逻辑。如pre类型的过滤器，可以通过对请求的验证来决定是否将请求路由到服务上；如post类型的过滤器，可以对服务响应结果做加工处理（如为每个响应增加footer数据）。
     *
     *      RequestContext.getCurrentContext(); 获取当前Request上下文对象
     *      requestContext.getRequest(); 获取当前请求
     *      requestContext.setSendZuulResponse(false);  拦截请求
     *
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        // 继续执行
        return null;
    }
}
