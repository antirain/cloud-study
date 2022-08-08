package com.hyf.cloud.filter;

import com.hyf.cloud.feign.ProviderFeign;
import com.hyf.cloud.feign.SecuritypePermissionFeign;
import com.hyf.cloud.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/1 15:23
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Autowired
    private SecuritypePermissionFeign securitypePermissionFeign;
    @Autowired
    private ProviderFeign providerFeign;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String id = exchange.getRequest().getQueryParams().getFirst("id");
        if  (StringUtils.isEmpty(id)){
            log.debug("缺少id,非法请求");
            exchange.getResponse().setStatusCode(HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}