## 应用概述
spring-boot web 示例项目  

## 流水线阶段描述 
```
Step 1 ,Checkout Git     => 获取代码   
Step 2 ,Unit Test        => 单元测试
Step 3 ,Build Jar        => 构建jar包
Step 4 ,Code Check       => 代码检测   
Step 5 ,Build Image      => 构建docker镜像   
Step 6 ,Deploy           => 滚动更新,应用健康探测  
Step 7 ,RollBack         => 手动回滚和保存稳定版本       
```

## 工具链描述 
```
Jenkins      : 项目构建   
SonarQube    : 代码检测   
Gitlab       : 代码仓库   
Harbor       : docker镜像仓库   
Kubernetes   : 集群管理   
```
## CI/CD 功能
手动执行jenkins发布、回滚   

## 演示说明
基于jenkins完成devops pipeline流程，缺少 需求管理融入     

##### 演示环境  
物理机2台 2c|8g  
1台: jenkins + k8s master  
1台: gitlab + harbor + sonarqube  

##### 演示场景  
1、单元测试 失败  
2、代码检测 失败  
3、发布成功再备份    
4、发布失败再回滚   

##### 其它:监控和日志    
- k8s监控  
lens   

- 硬件监控  
telegraf + influxDB + grafana  

- 日志搜集   
1、fluent-bit/filebeat -> kafka <- flink -> clickhouse  
2、web展示  

