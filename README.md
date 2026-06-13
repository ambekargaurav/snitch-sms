# Snitch SMS

SMS Message Router - A Spring Boot application for routing and managing SMS messages.

## Overview

Snitch SMS is a microservice built with Spring Boot that provides SMS message routing capabilities. The service is designed to handle SMS message processing and management with a RESTful API interface.

## Tech Stack

- **Java 21**
- **Spring Boot 4.1.0**
- **Spring Web** - REST API framework
- **Spring Data JPA** - Database abstraction
- **H2 Database** - In-memory database for development
- **Maven** - Build tool

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven (included via mvnw wrapper)

### Running the Application

```bash
./mvnw spring-boot:run
```

Or using the Maven wrapper on Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start on the default Spring Boot port (8080).

### Health Check

Once running, verify the service status:

```bash
curl http://localhost:8080/health
```

### Database Console

H2 console is available at: `http://localhost:8080/h2-console`

Connection details:
- JDBC URL: `jdbc:h2:mem:smsdb`
- Username: `sa`
- Password: (empty)

## Project Status

Currently in early development phase with basic Spring Boot infrastructure and health check endpoint.
