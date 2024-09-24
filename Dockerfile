# 基础镜像
FROM openjdk:17-jdk-alpine

# 拷贝jar包
COPY shs-0.0.1.jar /app/shs.jar

# 设置环境变量，告诉Spring Boot配置文件的位置
ENV spring_config_location=file:/app/application.yml

#开放端口
EXPOSE 9999

# 入口
ENTRYPOINT ["java", "-jar", "/app/shs.jar"]
