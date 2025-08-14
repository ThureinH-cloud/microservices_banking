FROM openjdk:17-jdk-slim
COPY target/loans-0.0.1-SNAPSHOT.jar loans-microservices
LABEL authors="user"

ENTRYPOINT ["java", "-jar","loans-microservices"]