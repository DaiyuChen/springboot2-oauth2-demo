## 介绍
此项目是一个开源的基于Oauth2协议的认证、授权系统Demo, 感兴趣的朋友可以参考一下.

### 目录结构
```
├─auth-server          认证服务(基于redis实现session共享, 可以多节点部署)
├─auth-client          Oauth client服务(未实现)
├─resource-server      资源服务
```
### 环境需求
- JDK8+
- Mysql5+
- Redis

### 调整配置
需要修改auth-server/src/main/resources/application.yml中数据库和redis配置

### 测试项目
1. 创建数据库oauth2
2. 运行数据库初始化脚本
   ```
   source  auth-server/src/config/init.sql
   ``` 
3. 启动认证服务器 auth-server(按照正常spring boot项目启动方式启动)
4. 启动资源服务器 resource-server(按照正常spring boot项目启动方式启)
```
测试客户(Client)
test / 123456

测试用户(User)
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