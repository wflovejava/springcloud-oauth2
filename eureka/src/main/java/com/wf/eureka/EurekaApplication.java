package com.wf.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: wf
 * @Date: 2020-05-07 11:17
 * @describe
 */
@SpringBootApplication
@EnableEurekaServer
@EnableEurekaClient
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class);
        System.out.println("EurekaApplication 启动成功！");
    }
}
