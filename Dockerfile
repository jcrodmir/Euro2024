# Etapa 1: Compilar el JAR
FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/eurocopa2024-0.0.1.jar api_eurocopa.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api_eurocopa.jar"]
