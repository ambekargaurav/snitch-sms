# Snitch SMS

SMS Message Router - A Spring Boot application for routing and managing SMS messages.

## Overview

Snitch SMS is a microservice built with Spring Boot that provides SMS message routing capabilities. The service handles SMS message creation, tracking, and retrieval through a RESTful API with status monitoring (PENDING, SENT, DELIVERED, BLOCKED).

## Tech Stack

- **Java 21**
- **Spring Boot 4.1.0**
- **Spring Web** - REST API framework
- **Lombok** - Reduce boilerplate code
- **Spring Validation** - Request validation
- **Logback** - Logging framework
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

## API Endpoints

### POST /messages
Create a new SMS message with destination number, content, and format. Returns message UUID and initial status (PENDING).

### GET /messages/{id}
Retrieve a message by UUID including all details and current status.

### GET /health
Health check endpoint to verify service status.

## Data Model

**Message Entity**
- `id` - UUID identifier
- `destinationNumber` - Recipient phone number
- `content` - SMS message content
- `format` - Message format
- `status` - Message status (PENDING, SENT, DELIVERED, BLOCKED)
- `createdAt` - Timestamp of message creation

## Project Status

Core messaging functionality implemented with message creation, retrieval, and status tracking. Uses in-memory storage with UUID identifiers and comprehensive logging. Unit tests cover message creation, retrieval, and error handling scenarios.

## Testing

Run tests using Maven:

```bash
./mvnw test
```

Or on Windows:

```bash
mvnw.cmd test
```

Test coverage includes:
- Message creation with PENDING status
- Unique UUID assignment
- Message retrieval by ID
- Error handling for non-existent messages
