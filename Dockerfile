FROM maven:3.8-adoptopenjdk-11 AS builder
COPY . .
COPY target/management.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]