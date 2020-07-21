package com.imooc.sellgateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.imooc.sellgateway.exception.RateLimitException;
import com.netflix.zuul.ZuulFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流拦截器
 */
@Component
public class RateLimiterFilter extends ZuulFilter {

    /**
     * 令牌桶，没秒钟往里面放多少令牌
     * 一秒钟只能有100个请求访问
     * 防止恶意访问，短信轰炸
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (!RATE_LIMITER.tryAcquire()) {
            //返回可使用两种方式
//            //第一种方式
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

//            //第二种方式
            throw new RateLimitException();
        }
            return null;
    }
}
