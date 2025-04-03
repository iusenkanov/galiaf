# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy application sources and sentry-agent.jar
COPY src ./src
COPY target/sentry-agent.jar /app/sentry-agent.jar

# Build application JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy app.jar and sentry-agent.jar from builder stage
COPY --from=builder /app/target/analytics-0.0.1-SNAPSHOT.jar app.jar
COPY --from=builder /app/sentry-agent.jar sentry-agent.jar

EXPOSE 8080

# Single entrypoint with SENTRY_AUTO_INIT and sentry agent
ENTRYPOINT ["sh", "-c", "SENTRY_AUTO_INIT=false OTEL_SDK_DISABLED=true SENTRY_DSN='https://2449b08ab0a8e4151685b0933b22f828@o4508681726918656.ingest.us.sentry.io/4508694441689088' java -javaagent:sentry-agent.jar -jar app.jar"]
