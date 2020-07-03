package com.wf.provide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 资源服务器
 * @Author: wf
 * @Date: 2020-05-07 13:17
 * @describe
 */
@SpringBootApplication(scanBasePackages = {"com.wf.base","com.wf.provide"})
@EnableEurekaClient
public class ProvideApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProvideApplication.class);
        System.out.println("ProvideApplication 启动成功！");
    }
}
