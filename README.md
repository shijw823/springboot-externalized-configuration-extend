# Spring Boot外部化配置属性源 扩展

> 注意：bootstrap.properties 需要修改 配置服务 相关信息
>
> 以下的扩展可以选取其中一种进行扩展，只是属性源的加载时机不太一样
## 1、基于EnvironmentPostProcessor 扩展
## 2、基于ApplicationEnvironmentPreparedEvent 扩展
## 3、基于SpringApplicationRunListener 扩展
## 4、基于ApplicationContextInitializer 扩展
> 关于与 Spring Cloud Config Client 整合，对外部化配置加载的扩展（绑定到Config Server，使用远端的property sources 初始化 `Environment`），参考源码
> `PropertySourceBootstrapConfiguration`（是对 `ApplicationContextInitializer` 的扩展）、`ConfigServicePropertySourceLocator#locate`
>
> 获取远端的property sources是 `RestTemplate` 通过向 http://{spring.cloud.config.uri}/{spring.application.name}/{spring.cloud.config.profile}/{spring.cloud.config.label} 发送 GET 请求方式获取的
## 5、基于ApplicationPreparedEvent 扩展

# `PropertySources` 的加载顺序

- 参考 `Application.printPropertySource`
- 参考 http://{host}:{port}/actuator/env

# 官方资料
https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#boot-features-external-config
