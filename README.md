# Banking Credit Management System

## Overview
This application is a Spring Boot-based web service for managing banking credits. It provides a comprehensive solution for handling different types of credits (Personal, Mortgage, Professional), client management, and repayment tracking.

## Technologies Used
- **Backend**: Java 21, Spring Boot 3.4.5
- **Database**: H2 Database (in-memory)
- **ORM**: Hibernate, JPA
- **API Documentation**: Swagger/OpenAPI
- **DTO Mapping**: MapStruct
- **Build Tool**: Maven

## Architecture
The application follows a layered architecture:
- **DAO Layer**: Based on Spring Data, JPA, and Hibernate
- **Service Layer**: Business logic with DTO/Entity mapping
- **Web Layer**: REST Controllers with Spring MVC

## Key Features
- Client management (CRUD operations)
- Credit management with support for three types:
  - Personal Credit (with purpose field)
  - Mortgage Credit (with property type field)
  - Professional Credit (with purpose and company name fields)
- Repayment tracking with different types (monthly payments, early repayments)
- Comprehensive REST API with proper documentation

## Domain Model
- **Client**: Can have multiple credits
- **Credit**: Base entity with common credit attributes
  - **Personal Credit**: For personal needs (car purchase, education, etc.)
  - **Mortgage Credit**: For property purchases (apartment, house, commercial)
  - **Professional Credit**: For business purposes
- **Repayment**: Tracks payments made against credits

## API Documentation
The API documentation is available through Swagger UI:
- URL: http://localhost:8086/swagger-ui.html
- API Docs: http://localhost:8086/api-docs

## Database Configuration
The application uses an H2 in-memory database:
- Console URL: http://localhost:8086/h2-console
- JDBC URL: jdbc:h2:mem:creditdb
- Username: sa
- Password: (empty)

## How to Run
1. Ensure you have Java 21 installed
2. Clone the repository
3. Navigate to the project directory
4. Run: `mvn spring-boot:run`
5. The application will start on port 8086
6. Access the API at http://localhost:8086/api/...
7. Access Swagger UI at http://localhost:8086/swagger-ui.html

## Project Structure
- `entities`: JPA entity classes
- `repositories`: Spring Data JPA repositories
- `dtos`: Data Transfer Objects
- `mappers`: MapStruct mappers for DTO/Entity conversion
- `services`: Business logic interfaces and implementations
- `controllers`: REST API controllers
- `config`: Application configuration

## Testing
The application includes a CommandLineRunner in the main class that populates the database with test data and performs basic DAO layer tests on startup.


Add link to frent end https://github.com/youssefoubens/Ouben-Said-Youssef-Exam-JEE_frentend.git