package com.wf.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: wf
 * @Date: 2020-05-07 13:23
 * @describe
 */
@SpringCloudApplication
@EnableZuulProxy

public class ApiGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class);
        System.out.println("ApiGateWayApplication 启动成功！");
    }
}
