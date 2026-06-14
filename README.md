# Snitch SMS

SMS Message Router - A Spring Boot application for routing and managing SMS messages.

## Overview

Snitch SMS is a microservice built with Spring Boot that provides SMS message routing capabilities. The service handles SMS message creation, validation, carrier routing, and tracking through a RESTful API with status monitoring (PENDING, SENT, DELIVERED, BLOCKED).

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
- `carrier` - Carrier assigned for delivery (TELSTRA, OPTUS, SPARK, GLOBAL)
- `status` - Message status (PENDING, SENT, DELIVERED, BLOCKED)
- `createdAt` - Timestamp of message creation

## Design Overview & Assumptions

This service implements a simplified SMS routing system using a layered, event-driven architecture.

### Key Assumptions

**Message Lifecycle**
- Messages follow the lifecycle: PENDING → SENT → DELIVERED/BLOCKED
- A message is marked as PENDING when initially created via the API
- A message transitions to SENT after successful validation and carrier routing
- Delivery confirmation is out of scope and not implemented
- Opt-out numbers are blocked before processing and marked as BLOCKED

**Phone Number Validation**
- AU: +61 followed by 9 digits
- NZ: +64 followed by 8–9 digits

### Event-Driven Processing

Message processing (validation, routing, and status update) is decoupled from API request handling using Spring's application events:
- MessageService publishes a MessageCreatedEvent after creating a message
- MessageCreatedListener consumes the event and delegates processing to MessageProcessor
- MessageProcessor handles validation, carrier routing, and status updates

### Future Scalability

The current event-based design is intentionally lightweight and can be evolved into a distributed system by replacing Spring application events with a message broker such as Kafka. This would allow asynchronous processing, horizontal scaling, and independent consumer services without changing core business logic.

## Project Status

Core messaging functionality implemented with message creation, retrieval, validation, carrier routing, and status tracking. Uses in-memory storage with UUID identifiers, event-driven processing, and comprehensive logging. Unit tests cover message creation, retrieval, and error handling scenarios.

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
