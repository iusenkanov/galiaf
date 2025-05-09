# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

# Build application JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

# Define a build-time variable for the Sentry release.
ARG SENTRY_RELEASE=dev
ENV SENTRY_RELEASE=$SENTRY_RELEASE

EXPOSE 8080

# Запускаем
ENTRYPOINT ["java", "-jar", "app.jar"]