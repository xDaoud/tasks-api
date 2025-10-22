 Task Manager API

A RESTful Task Manager API built with Spring Boot for creating, managing, and organizing tasks.

## Features
- Create, read, update, and delete tasks
- RESTful API design with proper HTTP status codes
- PostgreSQL database integration
- *User authentication and soft delete (in progress)*

## Tech Stack
- Java 21
- Spring Boot 3.x
- PostgreSQL
- Maven

## Installation & Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/xDaoud/tasks-api
   cd tasks-api
Set up PostgreSQL database and update application.properties

Run the application:

bash
mvn spring-boot:run
API will be available at: http://localhost:8080

## API Endpoints
Method	Endpoint	Description
GET	/api/tasks	Get all tasks
POST	/api/tasks	Create new task
GET	/api/tasks/{id}	Get task by ID
PUT	/api/tasks/{id}	Update task
DELETE	/api/tasks/{id}	Delete task
## Future Enhancements
Docker containerization

User authentication with JWT

Soft delete functionality
