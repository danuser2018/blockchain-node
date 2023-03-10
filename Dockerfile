FROM eclipse-temurin:17-jdk-alpine
LABEL org.opencontainers.image.source="https://github.com/danuser2018/blockchain-node"

RUN mkdir /opt/app
COPY /main/target/main-1.0.0.jar /opt/app/main-1.0.0.jar

CMD ["java","-jar","/opt/app/main-1.0.0.jar"]
