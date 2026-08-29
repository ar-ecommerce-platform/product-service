# product-service

Product catalog for the [ar-ecommerce-platform](https://github.com/ar-ecommerce-platform).

- **Port:** 8083
- **Persistence:** in-memory H2 (`products` table), seeded with 5 demo products (ids 1..5) on startup
- **Registers with:** Eureka (discovery-server :8761)
- Prices are stored as integer **cents**.

## Endpoints

Reached through the gateway as `/api/products/**`.

| Method | Path | Result |
|---|---|---|
| `GET` | `/products` | all products |
| `GET` | `/products/{id}` | one product, `404` if missing |

**API docs:** Swagger UI at `http://localhost:8083/swagger-ui.html` (OpenAPI JSON at `/v3/api-docs`).

## Run

Whole platform (recommended):

```bash
docker compose -f ../infra/compose/docker-compose.yml up -d --build
```

This service alone:

```bash
./gradlew bootRun
# or
docker build -t ecom/product-service . && docker run --rm -p 8083:8083 ecom/product-service
```

## Build & quality

```bash
./gradlew build          # compile + test + spotless + checkstyle (cyclomatic complexity <= 10) + jacoco report
./gradlew spotlessApply
```

Quality config is vendored: `gradle/quality.gradle`, `config/checkstyle/`.

## Config

| Variable | Default | Purpose |
|---|---|---|
| `SERVER_PORT` | `8083` | HTTP port |
| `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` | `http://localhost:8761/eureka/` | registry URL |

## Tech

Java 21 · Spring Boot 3.5.7 · Spring Data JPA + H2 · Bean Validation ·
Spring Cloud 2025.0.0 (`netflix-eureka-client`) · Gradle

See [infra/RUNBOOK.md](../infra/RUNBOOK.md) for the full platform runbook.
