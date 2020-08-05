##介绍
此项目是一个开源的基于Oauth2协议的认证、授权系统Demo

### 目录结构
```
├─auth-server          认证服务(实现了session共享, 可以多节点部署)
├─auth-client          Oauth client服务(未实现)
├─resource-server      资源服务
```
### 测试
1. 启动认证服务器 auth-server
2. 启动资源服务器 resource-server
```
测试client
test / 123456

测试账号
admin / 123456
```

### 获取access_token
授权码模式:
1. 获取授权码 http://localhost:9000/oauth/authorize?client_id=test&response_type=code
2. 通过授权码换取token
    ```
    POST http://localhost:9000/oauth/token
    
    Request body:
    grant_type:authorization_code
    code:516Dvy
    client_id:test
    client_secret:123456
    ```
密码模式: 
POST http://localhost:9000/oauth/token?username=admin&password=123456&grant_type=password&client_id=test&client_secret=123456

3. 访问资源服务 GET http://127.0.0.1:8000/api/test 测试access_token
```
Headers:
Authorization: Bearer {access_token}
```
### Auth-server 多节点测试
启动auth-server 端口分别为 9000/9001

1. 通过9000节点获取授权码 http://localhost:9000/oauth/authorize?client_id=test&response_type=code

2. 通过9001节点换取token POST http://localhost:9000/oauth/token

resource-server
1. check-token-uri 配置为9000节点, 通过access token访问测试接口 GET http://127.0.0.1:8000/api/test
```
security:
  oauth2:resource:
      check-token-uri: http://localhost:9000/oauth/check_token
```      
      
2. check-token-uri 配置为9001节点, 通过access token访问测试接口 GET http://127.0.0.1:8000/api/test
```
security:
  oauth2:resource:
      check-token-uri: http://localhost:9001/oauth/check_token
```