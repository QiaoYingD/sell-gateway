package com.imooc.sellgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SellGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellGatewayApplication.class, args);
    }

}
