## 应用概述
spring-boot web 示例项目  

## 流水线阶段描述 
```
Step 1 ,Checkout Git     => 获取代码   
Step 2 ,Unit Test        => 单元测试
Step 3 ,Code Check       => 代码检测   
Step 4 ,Build Jar        => 构建jar包
Step 5 ,Build Image      => 构建docker镜像   
Step 6 ,Deploy           => 滚动更新,应用健康探测  
Step 7 ,RollBack         => 手动回滚和保存稳定版本       
```

## 工具链描述 
```
Gradle       : java包管理工具   
Jenkins      : 项目构建   
SonarQube    : 代码检测   
Gitlab       : 代码仓库，可触发jenkins构建   
Harbor       : docker镜像仓库   
kubernetes   : docker集群管理   
```
## CI/CD 功能
测试环境, 项目开发人员提交代码后，自动触发jenkins构建完任务后，钉钉机器人通知项目组结果   
生产环境, 手动执行jenkins发布、回滚  

## 演示说明
基于jenkins完成devops pipeline流程，缺少 需求管理融入、度量指标展示  
jenkins : http://106.14.120.62:9999    
admin admin123456   

* 对devops的执行人员想法  
1、6名开发为1组，组长做为 devops 执行人，1个运维同学大约可以负担 10组的维护    

* 演示环境  
物理机2台 2c|8g
1台: jenkins + k8s master
1台: gitlab + harbor + sonarqube 

* 演示步骤  
1、Unit Test 失败  
2、正确发布  
3、错误发布回滚  
4、截图展示资源和日志的监控(栈日志多行搜集)  

* 度量指标  
- 监控  
用的lens   

- 日志搜集   
1、fluent-bit -> kafka <- flink -> clickhouse  
2、web展示  

