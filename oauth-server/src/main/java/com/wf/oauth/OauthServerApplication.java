package com.wf.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 授权服务器
 * @Author: wf
 * @Date: 2020-05-07 11:07
 * @describe
 */
@SpringBootApplication
@MapperScan("com.wf.oauth.db")
public class OauthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class);
        System.out.println("OauthServerApplication 服务启动成功！");
    }
}
