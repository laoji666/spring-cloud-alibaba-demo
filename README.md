# 微服务demo

#####  后端部分

  技术 | 说明 | 地址
  ----|----|----
  Spring Boot | 新一代 JavaEE 开发标准 | [GitHub](https://github.com/spring-projects/spring-boot)
  Spring Cloud Alibaba | 阿里巴巴基于 Spring Cloud 编程模型的微服务生态 | [GitHub](https://github.com/alibaba/spring-cloud-alibaba)
  Spring Cloud Alibaba Dubbo | 与 Spring Cloud Alibaba 生态相结合的高性能 Java RPC 框架 | [GitHub](https://github.com/apache/dubbo)
  Spring Cloud Alibaba RocketMQ | 分布式消息系统 | [GitHub](https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/rocketmq-example/readme.md)
  Spring Security oAuth2 | 安全认证和授权框架 | [GitHub](https://github.com/spring-projects/spring-security-oauth)
  TkMyBatis | 基于 MyBatis 二次开发的轻量级框架，用于简化 MyBatis 操作 | [GitHub](https://github.com/abel533/Mapper)
  MyBatisGenerator | Maven 插件，用于 MyBatis 相关代码生成 | [官网](http://www.mybatis.org/generator/)
  MybatisCodeHelper | Intellij IDEA 插件，用于 MyBatis 相关代码生成 | [官网](https://plugins.jetbrains.com/plugin/9837-mybatiscodehelperpro)
  PageHelper | MyBatis 分页插件 | [GitHub](https://github.com/pagehelper/Mybatis-PageHelper)
  Swagger | API 文档生成工具 | [GitHub](https://github.com/swagger-api/swagger-ui)
  HikariCP | 数据库连接池 | [GitHub](https://github.com/brettwooldridge/HikariCP)
  OKHttp3 | 轻量级网络框架 | [GitHub](https://github.com/square/okhttp)
  OpenFeign | 声明式 HTTP 客户端 | [GitHub](https://github.com/OpenFeign/feign)
  UserAgentUtils | 用户代理检查工具 | [GitHub](https://github.com/HaraldWalker/user-agent-utils)

#####  后台前端部分
技术 | 说明 | 地址
----|----|----
Vue | 前端框架，MVVM 模式的实现者 | [GitHub](https://github.com/vuejs/vue)
Vue CLI | Vue 脚手架，基于 NodeJS | [GitHub](https://github.com/vuejs/vue-cli)
Vue Router | Vue 路由框架 | [GitHub](https://github.com/vuejs/vue-router)
Vuex | Vue 全局状态管理框架 | [GitHub](https://github.com/vuejs/vuex)
Axios | 前端 HTTP 框架 | [GitHub](https://github.com/axios/axios)
Element UI | 饿了么 UI 框架 | [官网](https://element.eleme.cn)
Vue Element Admin | 基于 Element UI 的前端后台解决方案 | [GitHub](https://github.com/PanJiaChen/vue-element-admin)
Vue Image Crop Upload | Vue 图片剪裁上传组件 | [GitHub](https://github.com/dai-siki/vue-image-crop-upload)




###### 模块介绍

- dependencies	统一依赖版本管理模块
- gateway   网关
- cloud   云服务
  - cloud-upload-feign   将 上传的`Feign` 接口单独暴露出来 
  - cloud-upload-service   文件上传的服务 
  - cloud-message-api    消息生产   接口 
  - cloud-message-dto    消息生产   dto
  - cloud-message-feign  消息生产  feign接口
  - cloud-message-service   消息生产 服务
- message  消息消费者
  - message-admin-login-log   管路员登录日志消费者
- commons   公共对象存放
  - commons-dto   存放公共的dto
  - commons-utils   存放公共的工具类
- configuration   公共配置模块
  - configuration-feign    feign配置  这里配置了一个拦截器 用于往请求头中带上token
  - configuration-sentinel   sentinel配置  熔断 限流  降级
- provider  服务提供者模块
  - provider-admin-api	系统管理员接口
  - provider-admin-service   系统管理员服务
  - provider-admin-login-log-api   管理员登录日志接口
  - provider-admin-login-log-service   管理员登录日志服务
- business    业务模块
  - business-reg-service    注册业务
  - business-oauth2-service    认证业务
  - business-profile-service    个人信息业务模块(资源服务器)  利用feign 客户端体验携带访问令牌的请求效果 
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

关于RocketMQ  Windows部署流程

1. bin目录下   执行   
   1. start mqnamesrv.cmd 
   2.  start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true 
      1.  假如弹出提示框提示‘错误: 找不到或无法加载主类 xxxxxx’。打开runbroker.cmd，然后将‘%CLASSPATH%’加上英文双引号。保存并重新执行start语句。 
2. 可视化插件部署   https://github.com/apache/rocketmq-externals.git 
   1.  下载完成之后，进入‘rocketmq-externals\rocketmq-console\src\main\resources’文件夹，打开‘application.properties’进行配置。 
      1. 主要配置端口  运行端口和rocketmq部署端口
   2.  进入‘\rocketmq-externals\rocketmq-console’文件夹，执行‘mvn clean package -Dmaven.test.skip=true’，编译生成。 
   3. 编译成功之后，Cmd进入‘target’文件夹，执行‘java -jar rocketmq-console-ng-1.0.1.jar’，启动‘rocketmq-console-ng-1.0.0.jar’。

部署教程 =>  https://www.jianshu.com/p/4a275e779afa 



 资料来源 ： [Nacos](https://github.com/alibaba/Nacos)  [SpringCloudAlibab](https://github.com/alibaba/spring-cloud-alibaba)   [Dubbo](https://github.com/apache/dubbo)   [Spring](https://spring.io/)   [Sentinel](https://github.com/alibaba/Sentinel)  
