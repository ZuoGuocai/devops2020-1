build:
	./gradlew bootJar

run:
	./gradlew bootRun

devdocker:
	./gradlew bootJar
	docker build ./devops/docker/. -t harbor.shanghaicang.com.cn:1443/fx/devops-test:1.0.0

rundocker
    docker run -d -p 25001:30001 --name devopstest harbor.shanghaicang.com.cn:1443/fx/devops-test:1.0.0
