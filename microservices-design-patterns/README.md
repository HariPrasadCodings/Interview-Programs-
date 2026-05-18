# Microservices Design Patterns

A comprehensive Spring Boot microservices project demonstrating **9 key design patterns** commonly asked in Java interviews (4-5 years experience level). Built with **Spring Boot 3.2.5**, **Spring Cloud 2023.0.1**, and **Java 21**.

## Architecture Overview

```
                            ┌─────────────────┐
                            │  Config Server   │
                            │     :8888        │
                            └────────┬────────┘
                                     │
┌──────────┐    ┌──────────────┐    ┌┴────────────────┐
│  Client   │───▶  API Gateway  │───▶ Discovery Server │
│           │    │    :8080     │    │  (Eureka) :8761  │
└──────────┘    └──────┬───────┘    └──────────────────┘
                       │
        ┌──────────────┼──────────────┬──────────────┐
        ▼              ▼              ▼              ▼
┌──────────────┐ ┌───────────┐ ┌───────────┐ ┌───────────┐
│ Auth Service │ │  Product   │ │   Order   │ │  Payment  │
│    :9000     │ │  Service   │ │  Service  │ │  Service  │
│              │ │   :8082    │ │   :8081   │ │   :8083   │
│   (JWT)      │ │  (CQRS)   │ │(CircuitBr)│ │ (DB/Svc)  │
└──────────────┘ └───────────┘ └─────┬─────┘ └───────────┘
                                     │
                 ┌───────────────────┼───────────────────┐
                 ▼                   ▼                   ▼
          ┌───────────┐     ┌──────────────┐    ┌──────────────┐
          │ Inventory  │     │ Notification │    │    Saga      │
          │  Service   │     │   Service    │    │ Orchestrator │
          │   :8084    │     │    :8085     │    │    :8086     │
          │  (Kafka)   │     │(Event-Driven)│    │(Saga Pattern)│
          └───────────┘     └──────────────┘    └──────────────┘
```

## Design Patterns

| # | Pattern | Module | Description |
|---|---------|--------|-------------|
| 1 | **API Gateway** | `api-gateway` | Single entry point with routing, JWT auth filter, rate limiting, and circuit breakers |
| 2 | **Service Registry** | `discovery-server` | Netflix Eureka for service registration and discovery |
| 3 | **Service Communication** | `order-service` | Three approaches: OpenFeign, RestTemplate, WebClient |
| 4 | **Database per Service** | All services | Each service owns its database (MySQL, PostgreSQL, MongoDB, H2) |
| 5 | **CQRS** | `product-service` | Separate write model (H2/JPA) and read model (MongoDB), synced via Kafka |
| 6 | **Saga** | `saga-orchestrator` | Both orchestration (central coordinator) and choreography (event-driven) |
| 7 | **Centralized Config** | `config-server` | Spring Cloud Config with native profile for externalized configuration |
| 8 | **Circuit Breaker** | `order-service` | Resilience4j circuit breaker, retry, and rate limiter with fallbacks |
| 9 | **Async Messaging** | `inventory-service`, `notification-service` | Apache Kafka for event-driven communication |

## Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Language |
| Spring Boot | 3.2.5 | Application framework |
| Spring Cloud | 2023.0.1 | Distributed systems toolkit |
| Spring Cloud Gateway | - | API Gateway (reactive) |
| Netflix Eureka | - | Service discovery |
| OpenFeign | - | Declarative HTTP clients |
| Resilience4j | - | Circuit breaker, retry, rate limiter |
| Apache Kafka | 7.5.0 (Confluent) | Event streaming |
| MongoDB | 7.0 | CQRS read model |
| MySQL | 8.0 | Order database |
| PostgreSQL | 16 | Payment database |
| H2 | - | In-memory dev databases |
| JJWT | 0.12.5 | JWT authentication |
| Lombok | - | Boilerplate reduction |
| Docker | - | Containerization |
| Maven | 3.9 | Build tool |

## Project Structure

```
microservices-design-patterns/
├── pom.xml                      # Parent POM with dependency management
├── common-dto/                  # Shared DTOs, events, and enums
├── config-server/               # Centralized configuration (port 8888)
├── discovery-server/            # Eureka service registry (port 8761)
├── api-gateway/                 # API Gateway with filters (port 8080)
├── auth-service/                # JWT authentication (port 9000)
├── product-service/             # CQRS pattern (port 8082)
├── order-service/               # Circuit breaker + service communication (port 8081)
├── payment-service/             # Database per service (port 8083)
├── inventory-service/           # Kafka async messaging (port 8084)
├── notification-service/        # Event-driven notifications (port 8085)
├── saga-orchestrator/           # Saga pattern (port 8086)
└── docker/                      # Docker Compose + Dockerfile
```

## Service Details

### common-dto
Shared library containing:
- **DTOs:** `ProductDto`, `OrderDto`, `PaymentDto`, `InventoryDto`, `NotificationDto`, `ApiResponse<T>`
- **Events:** `OrderEvent`, `PaymentEvent`, `InventoryEvent`, `NotificationEvent`
- **Enums:** `OrderStatus`, `PaymentStatus`, `SagaStatus`

### config-server (:8888)
Spring Cloud Config Server with native profile. Provides centralized configuration for all services from `config-repo/` directory.

### discovery-server (:8761)
Netflix Eureka Server for service registration and discovery. All services register here and use `lb://service-name` for load-balanced communication.

### api-gateway (:8080)
Spring Cloud Gateway with:
- **AuthenticationFilter** — validates JWT tokens, propagates `X-Auth-User` and `X-Auth-Role` headers
- **LoggingFilter** — global request/response logging with timing
- **RateLimiterConfig** — IP, user, and path-based rate limiting (Redis-backed)
- **CircuitBreaker** per downstream route

| Route | Target | Auth Required |
|-------|--------|---------------|
| `/api/auth/**` | auth-service | No |
| `/api/products/**` | product-service | Yes |
| `/api/orders/**` | order-service | Yes |
| `/api/payments/**` | payment-service | Yes |
| `/api/inventory/**` | inventory-service | Yes |

### auth-service (:9000)
JWT-based authentication with Spring Security 6:
- `POST /auth/register` — register a new user
- `POST /auth/login` — authenticate and receive JWT
- `GET /auth/validate?token=...` — validate a token

### product-service (:8082) — CQRS Pattern
Separate write and read models:
- **Write side:** H2 (JPA) → `ProductCommandController` → `ProductCommandService`
- **Read side:** MongoDB → `ProductQueryController` → `ProductQueryService`
- **Sync:** Kafka `product-events` topic → `ProductEventHandler` updates the read model

```
Write: POST/PUT/DELETE → H2 → Kafka (product-events)
                                    ↓
Read:  GET ← MongoDB ← ProductEventHandler
```

### order-service (:8081) — Circuit Breaker + Service Communication
Demonstrates three inter-service communication patterns:
- **OpenFeign** — `ProductFeignClient`, `InventoryFeignClient`
- **RestTemplate** — `ProductRestTemplateClient` with `@LoadBalanced`
- **WebClient** — `ProductWebClientService` (reactive)

Resilience4j configuration:
- **Circuit Breaker:** sliding window of 10, 50% failure threshold, 10s open state
- **Retry:** max 3 attempts, 1s wait
- **Rate Limiter:** 20 calls/second

### payment-service (:8083) — Database per Service
Owns its isolated database. Publishes `PaymentEvent` to Kafka. Supports saga compensation via `refundPayment()`. Simulated failures for amounts ending in `.99`.

### inventory-service (:8084) — Async Messaging
Listens to `order-events` Kafka topic for saga choreography:
- `CREATED` → reserves inventory
- `CANCELLED` / `PAYMENT_FAILED` → releases inventory

Publishes `InventoryEvent` to `inventory-events` topic.

### notification-service (:8085) — Event-Driven
Consumes events from multiple Kafka topics:
- `order-events` → order confirmation, cancellation, shipping, delivery notifications
- `payment-events` → payment success, failure, refund notifications

Simulates EMAIL, SMS, and PUSH delivery channels.

### saga-orchestrator (:8086) — Saga Pattern
Two saga implementations:

**Orchestration (synchronous):**
```
SagaOrchestrator → InventoryReserveStep → PaymentProcessStep → OrderConfirmStep
                 ← compensate() on failure (reverse order)
```

**Choreography (event-driven):**
```
OrderEvent(CREATED) → InventoryEvent(RESERVED) → PaymentEvent(COMPLETED) → OrderEvent(CONFIRMED)
                   ← compensating events on failure
```

## Getting Started

### Prerequisites
- Java 21
- Maven 3.9+
- Docker & Docker Compose

### 1. Start Infrastructure

```bash
cd docker
docker-compose up -d
```

This starts Zookeeper, Kafka, MySQL, PostgreSQL, and MongoDB.

### 2. Build the Project

```bash
mvn clean compile
```

### 3. Start Services (in order)

```bash
# 1. Config Server (must start first)
cd config-server && mvn spring-boot:run

# 2. Discovery Server
cd discovery-server && mvn spring-boot:run

# 3. API Gateway
cd api-gateway && mvn spring-boot:run

# 4. Auth Service
cd auth-service && mvn spring-boot:run

# 5. Business Services (can start in parallel)
cd product-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd payment-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run
cd notification-service && mvn spring-boot:run

# 6. Saga Orchestrator
cd saga-orchestrator && mvn spring-boot:run
```

### 4. Build Docker Images

```bash
# Build a specific service
docker build --build-arg MODULE_NAME=order-service -t order-service:latest -f docker/Dockerfile .
```

## API Examples

### Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "john", "email": "john@example.com", "password": "secret123"}'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "john", "password": "secret123"}'
```

### Create a Product (with JWT)
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"name": "Laptop", "description": "Gaming laptop", "price": 999.99, "category": "Electronics", "quantity": 50}'
```

### Place an Order
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"userId": 1, "items": [{"productId": 1, "quantity": 2, "unitPrice": 999.99}]}'
```

### Start a Saga (Orchestration)
```bash
curl -X POST "http://localhost:8080/api/saga/orchestration/start?orderId=1" \
  -H "Authorization: Bearer <token>"
```

## Monitoring

Each service exposes Spring Boot Actuator endpoints:

| Endpoint | Description |
|----------|-------------|
| `/actuator/health` | Service health status |
| `/actuator/info` | Service information |
| `/actuator/metrics` | Application metrics |
| `/actuator/circuitbreakers` | Circuit breaker states (order-service, api-gateway) |

Eureka dashboard: http://localhost:8761

## Interview Talking Points

- **Why API Gateway?** — Single entry point, cross-cutting concerns (auth, rate limiting, logging), client simplification
- **Why Eureka?** — Client-side discovery, automatic load balancing with `lb://`, health-check based eviction
- **Feign vs RestTemplate vs WebClient?** — Feign is declarative and concise; RestTemplate is imperative and synchronous; WebClient is reactive and non-blocking
- **Why CQRS?** — Separate read/write scaling, optimized query models, eventual consistency via events
- **Saga vs 2PC?** — Sagas avoid distributed locks, better availability, compensating transactions instead of rollback
- **Orchestration vs Choreography?** — Orchestration has central control and easier debugging; choreography is more decoupled but harder to trace
- **Why Circuit Breaker?** — Prevents cascading failures, provides fallback responses, self-healing via half-open state
- **Why Kafka?** — Durable event log, decoupled producers/consumers, replay capability, partition-based parallelism
- **Database per Service?** — Loose coupling, independent scaling, polyglot persistence (MySQL, PostgreSQL, MongoDB)
