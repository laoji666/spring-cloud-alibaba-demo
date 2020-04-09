# 微服务demo

###### 技术栈

- SpringBoot   2.1.7.RELEASE
- SpringCloud  Greenwich.SR2
- SpringCloudAlibaba  2.1.0.RELEASE
- AlibabaNacos  1.1.3
- SpringSecurityOauth2
- SpringCloudGateway
- Apache Dubbo   2.7.3
- TK.MyBatis   2.1.5 
- HikariCp  3.2.0  
- OkHttp3   4.1.0   feign.okhttp3   10.2.3
- Aliyun OSS  3.6.0

###### 模块介绍

- dependencies	统一依赖版本管理模块
- gateway   网关
- cloud   云服务
  - cloud-upload    阿里云OSS文件上传
    - cloud-upload-feign   将 上传的`Feign` 接口单独暴露出来 
    - cloud-upload-service   文件上传的服务 
- commons   公共对象存放
  - commons-dto   存放公共的dto
  - commons-utils   存放公共的工具类
- configuration   公共配置模块
  - configuration-feign    feign配置  这里配置了一个拦截器 用于往请求头中带上token
  - configuration-sentinel   sentinel配置  熔断 限流  降级
- provider  服务提供者模块
  - ums-admin-provider-api	系统管理员接口
  - ums-admin-provider-service   系统管理员服务
- business    业务模块
  - business-reg    注册业务
  - business-oauth2    认证业务
  - business-profile    个人信息业务模块(资源服务器)  利用feign 客户端体验携带访问令牌的请求效果 
    - business-profile-feign     将 `Feign` 接口单独暴露出来 
    - business-profile-service    提供个人信息的服务
- front   前端代码
  - vue-admin    基于vue-admin-template的vue后台管理模板



#### Sentinel启动

在jar包目录启动  运行如下命令

```
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
//从 Sentinel 1.6.0 起，Sentinel 控制台引入基本的 登录 功能，默认用户名和密码都是 sentinel
//用户可以通过如下参数进行配置

-Dsentinel.dashboard.auth.username=sentinel 用于指定控制台的登录用户名为 sentinel
-Dsentinel.dashboard.auth.password=123456 用于指定控制台的登录密码为 123456；如果省略这两个参数，默认用户和密码均为 sentinel
-Dserver.servlet.session.timeout=7200 用于指定 Spring Boot 服务端 session 的过期时间，如 7200 表示 7200 秒；60m 表示 60 分钟，默认为 30 分钟
```

 在启动时，需要在 JVM 中添加以下启动参数 

```
-Djava.net.preferIPv4Stack=true
-Dcsp.sentinel.api.port=8720
-Dproject.name=ums-admin-provider
-Dcsp.sentinel.dashboard.server=127.0.0.1:8080
```

参数说明

```
-Dcsp.sentinel.api.port=客户端端口，用于上报信息，默认 8720 即可，Sentinel 发现端口冲突会自动递增
-Dproject.name=显示在控制台上的应用名称
-Dcsp.sentinel.dashboard.server=控制台地址
```





 资料来源 ： [Nacos](https://github.com/alibaba/Nacos)  [SpringCloudAlibab](https://github.com/alibaba/spring-cloud-alibaba)   [Dubbo](https://github.com/apache/dubbo)   [Spring](https://spring.io/)   [Sentinel](https://github.com/alibaba/Sentinel)  