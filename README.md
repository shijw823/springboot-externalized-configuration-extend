# Spring Boot外部化配置属性源 扩展
> 以下的扩展可以选取其中一种进行扩展，只是属性源的加载时机不太一样
## 1、基于EnvironmentPostProcessor 扩展
## 2、基于ApplicationEnvironmentPreparedEvent 扩展
## 3、基于SpringApplicationRunListener 扩展
## 4、基于ApplicationContextInitializer 扩展
> 关于与 spring-cloud-config 整合，对外部化配置加载的扩展，参考源码
org.springframework.cloud.bootstrap.config.PropertySourceBootstrapConfiguration  、org.springframework.cloud.config.client.ConfigServicePropertySourceLocator#locate 是对 ApplicationContextInitializer 的扩展
## 5、基于ApplicationPreparedEvent 扩展

# 官方资料
https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#boot-features-external-config
