# Build Stage
FROM gradle:8.5-jdk21 AS builder
WORKDIR /workspace
COPY . .
RUN ./gradlew :discovery-server:bootJar --no-daemon

# RUN Stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /workspace/discovery-server/build/libs/discovery-server.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]