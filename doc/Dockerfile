# 基于哪个镜像
FROM java:8

# 将本地文件夹挂载到当前服务器
VOLUME /tmp

# 复制文件到容器
ADD gemini-admin-1.0.0-SNAPSHOT.jar admin.jar

# 声明需要暴露
EXPOSE 8001

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin.jar"]
