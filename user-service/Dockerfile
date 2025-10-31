# Build Stage
FROM gradle:8.5-jdk21 AS builder
WORKDIR /workspace
COPY . .
RUN ./gradlew :user-service:bootJar --no-daemon

# RUN Stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /workspace/user-service/build/libs/user-service.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]