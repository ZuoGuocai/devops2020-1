FROM openjdk:11.0.8-jdk

LABEL MAINTAINER=fubiao@gongchangtemai.com.cn
COPY devops-test-1.0.0-boot.jar devops-test-1.0.0-boot.jar

CMD ["java", "-jar", "-Dfile.encoding=UTF-8", "-Xlog:gc:gc.log", "-Xmx1g", "-Dspring.profiles.active=test",  "-Dlogging.level.root=WARN", "-Dlogging.level.org.springframework=INFO",  "devops-test-1.0.0-boot.jar" ]