package com.wf.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @Author: wf
 * @Date: 2020-05-07 11:26
 * @describe 认证授权配置类 使用redis存储token
 */
@Configuration
@EnableAuthorizationServer //开启oauth2的授权服务器的功能，  继承 AuthorizationServerConfigurerAdapter 进行 OAuth 授权服务器的配置。
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    /**
     * 客户端用户
     */
    @Value("${wf.oauth.clientid}")
    private String clientId;

    /**
     * 客户端密码
     */
    @Value("${wf.oauth.clientSecret}")
    private String  clientSecret;

    /**
     * 客户端 可授权的scope
     */
    @Value("${wf.oauth.scope}")
    private String  scope;

    /**
     * 用户认证 manager
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public MyRedisTokenStore tokenStore() {
        return new MyRedisTokenStore(connectionFactory);
    }


    /**
     * 配置使用的是AuthenticationManager实现的用户认证功能
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 使用默认的验证管理器和用户信息服务
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore());
    }

    /**
     * 设置 /oauth/check_token 端点 对应 CheckTokenEndpoint ，通过认证后可访问。
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    /**
     * client-id + client-secret 进行的客户端认证
     * 进行 Client 客户端的配置。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //定义哪些客户端可以注册到服务
        clients.inMemory()  //使用基于内存的 Client 存储器
                .withClient(clientId).secret(clientSecret) //客户端 账号、密码。
                 // 为什么要创建 Client 的 client-id 和 client-secret 呢？通过 client-id 编号和 client-secret，授权服务器可以知道调用的来源以及正确性。
                // 这样，即使“坏人”拿到 Access Token ，但是没有 client-id 编号和 client-secret，也不能和授权服务器发生有效的交互。
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")   // 支持的授权模式 密码模式 返回刷新令牌
                .accessTokenValiditySeconds(3600)//访问令牌的有效期 3600秒 = 2小时
                .refreshTokenValiditySeconds(864000)//刷新令牌的有效期 864000秒 = 10天
                .scopes(scope);//可授权的scope
        // .authorizedGrantTypes("password", "authorization_code", "refresh_token");
    }
}