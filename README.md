# springcloud-oauth2

项目介绍：
**api-gateway:统一路由，权限验证 **
eureka：服务发现和注册
oauth-server 认证和授权 通过密码方式
provide-service 用户登录 和其他接口服务

其他模块都依赖wf-base模块

****简单测试：****

访问oauth-server 获取token
1. POST 请求http://localhost:8082/oauth/token，使用密码授权方式
如图所示：
 ![Image text]( 这里是你的图片链接)
 
  返回结果说明：
  请求说明：
  通过 Basic Auth 的方式，填写 client-id + client-secret 作为用户名与密码，实现 Client 客户端有效性的认证。
  请求参数 grant_type 为 "password"，表示使用密码模式。
  请求参数 username 和 password，表示用户的用户名与密码。
  响应说明：
  响应字段 access_token 为访问令牌，后续客户端在访问资源服务器时，通过它作为身份的标识。
  响应字段 token_type 为令牌类型，一般是 bearer 或是 mac 类型。
  响应字段 expires_in 为访问令牌的过期时间，单位为秒。
  响应字段 scope 为权限范围
2. POST 请求http://localhost:8082/oauth/check_token，校验访问令牌的有效
 ![Image text]( 这里是你的图片链接)
 
 返回结果说明：
 返回用户和token具体信息
 
**1.直接访问provide-service服务**


a. 访问需要授权认证接口/test/test1
url: http://localhost:8081/test/test1

返回结果 访问被拒绝;出现拒绝访问
org.springframework.security.access.AccessDeniedException: Access is denied


b. 访问不需要授权认证接口/test/test1
 url: http://localhost:8081/test/test2
返回结果： test2 ok


**2.获取用户token**

http://localhost:9999/uaa/oauth/token?grant_type=password&username=admin&password=admin

**3.携带用户token访问provide-service服务**

a. 携带token访问需要授权认证接口/test/test1
url: http://localhost:8081/test/test1
返回成功