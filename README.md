
```markdown
# Task Manager API

A Spring Boot REST API for task management with user authentication.

## Features
- User registration and login
- Task creation and management  
- Role-based security (User/Admin)
- Users can only access their own data

## Tech Stack
- Java 21
- Spring Boot 3.x
- Spring Security
- PostgreSQL
- Maven

## Setup
1. Clone and run:
```bash
git clone https://github.com/xDaoud/tasks-api
cd tasks-api
mvn spring-boot:run
```

2. Configure PostgreSQL in `application.properties`

## API Endpoints

### Authentication
- POST `/auth/register` - Create user account
- POST `/auth/login` - Login and create session

### User Management  
- GET `/api/users/me` - Get current user profile
- PUT `/api/users/me` - Update user profile

### Admin Only
- GET `/api/admin/users` - List all users
- POST `/api/admin/users` - Create user
- DELETE `/api/admin/users/{id}` - Delete user

### Tasks
- GET `/api/tasks` - Get user's tasks
- POST `/api/tasks` - Create task
- PUT `/api/tasks/{id}` - Update task  
- DELETE `/api/tasks/{id}` - Delete task

## Security
- Session-based authentication
- BCrypt password hashing
- Automatic user detection from security context
```
