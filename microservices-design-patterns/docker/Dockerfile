# ================================================================
# MULTI-STAGE DOCKERFILE - Spring Boot Microservice
# ================================================================
# Generic Dockerfile template that builds any module from the
# multi-module Maven project. Pass the target module name via
# the MODULE_NAME build argument.
#
# Usage:
#   docker build \
#     --build-arg MODULE_NAME=order-service \
#     -t order-service:latest \
#     -f docker/Dockerfile .
#
# IMPORTANT: The build context (.) must be the project root
# (microservices-design-patterns/) so that the parent POM and
# common-dto module are accessible during the Maven build.
#
# Stages:
#   1. builder  - Compiles the project with Maven (JDK 21)
#   2. runtime  - Minimal JRE image to run the fat JAR
# ================================================================


# ==========================
# STAGE 1: BUILD
# ==========================
FROM maven:3.9-eclipse-temurin-21 AS builder

# The module to build (e.g., order-service, payment-service)
ARG MODULE_NAME

WORKDIR /app

# --- Dependency caching layer ---
# Copy only POM files first so Docker can cache the dependency
# download layer. Source code changes won't invalidate this cache.
COPY pom.xml ./pom.xml
COPY common-dto/pom.xml ./common-dto/pom.xml
COPY config-server/pom.xml ./config-server/pom.xml
COPY discovery-server/pom.xml ./discovery-server/pom.xml
COPY api-gateway/pom.xml ./api-gateway/pom.xml
COPY auth-service/pom.xml ./auth-service/pom.xml
COPY product-service/pom.xml ./product-service/pom.xml
COPY order-service/pom.xml ./order-service/pom.xml
COPY payment-service/pom.xml ./payment-service/pom.xml
COPY inventory-service/pom.xml ./inventory-service/pom.xml
COPY notification-service/pom.xml ./notification-service/pom.xml
COPY saga-orchestrator/pom.xml ./saga-orchestrator/pom.xml

# Download all dependencies (cached unless a POM changes)
RUN mvn dependency:go-offline -B -q

# --- Source code layer ---
# Copy the shared common-dto module (required by most services)
COPY common-dto/ ./common-dto/

# Install common-dto into the local Maven repo so that the
# target module can resolve it as a dependency
RUN mvn install -pl common-dto -am -DskipTests -B -q

# Copy only the target module's source code
COPY ${MODULE_NAME}/ ./${MODULE_NAME}/

# Build only the target module (-pl) and its local dependencies (-am)
# Skip tests for faster Docker builds; tests run in CI separately
RUN mvn package -pl ${MODULE_NAME} -am -DskipTests -B -q \
    && cp ${MODULE_NAME}/target/*.jar /app/application.jar


# ==========================
# STAGE 2: RUNTIME
# ==========================
FROM eclipse-temurin:21-jre-alpine AS runtime

# Re-declare ARG so it's available in this stage for labels/env
ARG MODULE_NAME

LABEL maintainer="interview-microservices"
LABEL module="${MODULE_NAME}"

# Create a non-root user for security best practices
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

# Copy the fat JAR from the builder stage
COPY --from=builder /app/application.jar ./application.jar

# Set ownership to the non-root user
RUN chown -R appuser:appgroup /app

USER appuser

# Spring Boot default port (override per service via SPRING_PROFILES_ACTIVE
# or SERVER_PORT environment variable in docker-compose)
EXPOSE 8080

# JVM tuning flags for containerized environments:
#   -XX:+UseContainerSupport    - respect cgroup memory/CPU limits
#   -XX:MaxRAMPercentage=75.0   - use at most 75% of container memory for heap
#   -Djava.security.egd         - faster random seed for startup
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar application.jar"]
