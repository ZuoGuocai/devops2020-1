## 应用概述
spring-boot web 示例项目  
所有构建都基于docker方式处理  

## 流水线阶段描述 
```
Step 1 ,Checkout from GitLab  => 拉取代码   
Step 2 ,Unit Test             => 单元测试
Step 3 ,SonarQube Check       => 代码检测   
Step 4 ,Build Jar             => 构建jar包
Step 5 ,Build Image           => 构建docker镜像   
Step 6 ,Deploy                => 滚动更新,应用健康探测
Step 7 ,RollBack              => 手动回滚最近稳定版本       
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

生产环境, 手动执行jenkins构建  

 
## 演示说明
jenkins : http://106.14.120.62:9999    
admin admin123456  

SonarQube : http://47.116.135.192:9000  
admin admin  