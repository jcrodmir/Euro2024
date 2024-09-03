# Etapa 1: Compilar el JAR
FROM maven:3.8.6-eclipse-temurin-17
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final
FROM eclipse-temurin:17-jdk-slim
COPY --from=build /eurocopa2024/target/eurocopa2024-0.0.1.jar api_eurocopa.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "eurocopa2024.jar"]
