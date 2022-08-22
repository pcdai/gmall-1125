package com.atguigu.gmall.gateway.filter;


/**
 * @author: dpc
 * @data: 2020/6/10,9:09
 */
//@Component
//public class MyGlobalFilter implements GlobalFilter, Ordered {
//
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("进入全局过滤器，拦截所有请求");
//        ServerHttpResponse response = exchange.getResponse();
//        //设置响应状态码
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        //放行
//        return chain.filter(exchange);
//        //拦截：return response.setComplete();
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
