package com.wf.provide.controller;

import com.wf.base.dto.Response;
import com.wf.base.util.ResponseUtils;
import com.wf.provide.dto.UserLoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wf
 * @Date: 2020-05-08 14:05
 * @describe
 */
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private OAuth2ClientProperties oauth2ClientProperties;

    @Autowired
    private ConsumerTokenServices tokenServices;



    @Value("${security.oauth2.access-token-uri}")
    private String accessTokenUri;




    /**
     * 用户登录
     * @param userLoginParam
     * @return
     */
    @PostMapping("/login")
    public Response<OAuth2AccessToken> login(@RequestBody UserLoginParam userLoginParam){

        if(userLoginParam.getUserName().equals("1") && userLoginParam.getUserPwd().equals("1")){

            //1. 用户登录成功
            String format = String.format("用户%s登录成功", userLoginParam.getUserName());
            System.out.println(format);
            //2. 获取token 扮演oauth2的客户端， 调用授权服务器的 /oauth/token 接口，使用密码模式进行授权，获得访问令牌。

            // <1> 创建 ResourceOwnerPasswordResourceDetails 对象
            ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
            resourceDetails.setAccessTokenUri(accessTokenUri);
            resourceDetails.setClientId(oauth2ClientProperties.getClientId());
            resourceDetails.setClientSecret(oauth2ClientProperties.getClientSecret());
            resourceDetails.setUsername(userLoginParam.getUserName());
            resourceDetails.setPassword(userLoginParam.getUserPwd());
            // <2> 创建 OAuth2RestTemplate 对象
            OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
            restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
            // <3> 获取访问令牌
            final OAuth2AccessToken accessToken = restTemplate.getAccessToken();

            return ResponseUtils.success(accessToken);

        }
        return ResponseUtils.success();
    }



    /**
     * 用户注销登出
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public Response logout(@RequestParam("token")String token){
        tokenServices.revokeToken(token);
        return ResponseUtils.success();
    }


}
