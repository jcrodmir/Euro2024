FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/eurocopa2024-0.0.1.jar
COPY ${JAR_FILE} api_eurocopa.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","api_eurcopa.jar"]