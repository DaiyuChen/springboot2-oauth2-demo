1. 启动认证服务器 auth-server(可多节点部署,已通过redis实现session共享)
2. 启动资源服务器 resource-server
测试client
test / 123456

测试账号
admin / 123456

授权码模式:
1. 获取授权码 http://localhost:9000/oauth/authorize?client_id=test&response_type=code
2. 通过授权码换取token
    POST http://localhost:9000/oauth/token
    
    Request body:
    grant_type:authorization_code
    code:516Dvy
    client_id:test
    client_secret:123456
    
密码模式: 
POST http://localhost:9000/oauth/token?username=admin&password=123456&grant_type=password&client_id=test_client&client_secret=123456


测试

auth-server启动两个节点 端口分别为 9000 9001

1. 通过9000节点获取授权码 http://localhost:9000/oauth/authorize?client_id=test&response_type=code

2. 通过9001节点换取token POST http://localhost:9000/oauth/token

resource-server
1. check-token-uri 配置为9000节点, 通过access token访问接口
security:
  oauth2:resource:
      check-token-uri: http://localhost:9000/oauth/check_token
      
      
2. check-token-uri 配置为9001节点, 通过access token访问接口
security:
  oauth2:resource:
      check-token-uri: http://localhost:9001/oauth/check_token
