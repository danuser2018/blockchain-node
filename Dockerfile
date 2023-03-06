FROM eclipse-temurin:17-jdk-alpine
RUN mkdir /opt/app
COPY /main-application/target/main-application-1.0.0.jar /opt/app/main-application-1.0.0.jar

CMD ["java","-jar","/opt/app/main-application-1.0.0.jar"]
