# springcloud-oauth2

****演示：****

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