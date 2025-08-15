FROM eclipse-temurin:21-jdk-jammy

COPY target/configserver-0.0.1-SNAPSHOT.jar config-server

LABEL authors="user"

ENTRYPOINT ["java", "-jar","config-server"]