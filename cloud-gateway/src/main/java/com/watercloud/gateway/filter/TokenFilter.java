package com.watercloud.gateway.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    @Value("${UnCheckPath}")
    private String UnCheckPath;
    @Autowired
    private JwtTool jwtTool;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
       ServerHttpRequest serverHttpRequest = exchange.getRequest();
       String requestUrl = serverHttpRequest.getPath().toString().trim();
       log.info("请求路径：{}",requestUrl);
       List anonUrls = Arrays.asList(UnCheckPath.split(","));
       if(StrUtil.isNotEmpty(UnCheckPath)){
           if(anonUrls.contains(requestUrl)){
               return chain.filter(exchange);
           }
       }
       List<String> tokens = serverHttpRequest.getHeaders().get("token");
       if(CollUtil.isEmpty(tokens)){
           ServerHttpResponse response = exchange.getResponse();
           DataBuffer buffer = response.bufferFactory().wrap(this.getResult("510","认证失败，无token！"));
           response.setStatusCode(HttpStatus.UNAUTHORIZED);
           response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
           return response.writeWith(Mono.just(buffer));
       }else {
           String token = tokens.get(0);
           try {
               Boolean check = jwtTool.check(token);
               if(check){
                   return chain.filter(exchange);
               }else{
                   ServerHttpResponse response = exchange.getResponse();
                   DataBuffer buffer = response.bufferFactory().wrap(this.getResult("510", "认证失败，错误token！"));
                   response.setStatusCode(HttpStatus.UNAUTHORIZED);
                   response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
                   return response.writeWith(Mono.just(buffer));
               }
           } catch (Exception e) {
               ServerHttpResponse response = exchange.getResponse();
               DataBuffer buffer = response.bufferFactory().wrap(this.getResult("510", "认证失败，错误token！"));
               response.setStatusCode(HttpStatus.UNAUTHORIZED);
               response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
               return response.writeWith(Mono.just(buffer));
           }
       }
    }

    private byte[] getResult(String code,String msg){
        JSONObject message = new JSONObject();
        message.put("success", false);
        message.put("code", code);
        message.put("message", msg);
        message.put("timestamp",System.currentTimeMillis());
        message.put("data","");
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        return bits;
    }
}
