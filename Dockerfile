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
COPY --from=builder /app/target/galiaf-1.0.3.jar app.jar
COPY --from=builder /app/target/sentry-agent.jar sentry-agent.jar
COPY sentry.agent.yaml /app/sentry.agent.yaml

ENV JAVA_TOOL_OPTIONS="-javaagent:/app/sentry-agent.jar -Dsentry.properties.file=/app/sentry.agent.yaml"

EXPOSE 8080

# Single entrypoint with SENTRY_AUTO_INIT and sentry agent
ENTRYPOINT ["sh", "-c", "SENTRY_AUTO_INIT=false OTEL_SDK_DISABLED=true SENTRY_DSN='https://a3730c176137847d3dea63b5b0fa9447@o4509091708928000.ingest.us.sentry.io/4509091710042112' java -javaagent:sentry-agent.jar -jar app.jar"]
