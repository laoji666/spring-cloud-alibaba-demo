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











资料来源  ： [Nacos](https://github.com/alibaba/Nacos)   [SpringCloudAlibab](https://github.com/alibaba/spring-cloud-alibaba)   [Dubbo](https://github.com/apache/dubbo)  [Spring](https://spring.io/) 

