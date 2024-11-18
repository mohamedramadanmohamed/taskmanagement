# Task Management API

This is a Spring Boot application that provides a Task Management system. It allows admins to create, update, delete, and search tasks, while users can view and search tasks based on different filters. The application also includes an authentication system using JWT for secure login.

## Features

- **Task Management**
  - Create tasks (Admin only)
  - Get task by ID (Users and Admins)
  - Get all tasks (Admin only)
  - Update tasks (Admin only)
  - Delete tasks (Admin only)
  - Search tasks by title, status, and due date
  - Filter tasks by title, status, and due date
  
- **Authentication**
  - Login using a username and password
  - Generate JWT tokens for authentication

## Endpoints

### Task Management

- **Create a task**  
  `POST /api/tasks`  
  Only accessible by admins. Accepts a `TaskDto` in the request body.
  
- **Get task by ID**  
  `GET /api/tasks/{id}`  
  Accessible by both users and admins. Returns the task details by ID.
  
- **Get all tasks**  
  `GET /api/tasks`  
  Only accessible by admins. Supports pagination with `page` and `size` query parameters.
  
- **Delete a task**  
  `DELETE /api/tasks/{id}`  
  Only accessible by admins. Deletes the task with the given ID.
  
- **Update a task**  
  `PUT /api/tasks/{id}`  
  Only accessible by admins. Accepts a `TaskDto` in the request body to update the task by ID.

- **Search tasks by title**  
  `GET /api/tasks/search?title={title}`  
  Accessible by both users and admins. Returns a list of tasks that contain the given title.

- **Get tasks by status**  
  `GET /api/tasks/status/{status}`  
  Accessible by both users and admins. Returns a list of tasks with the given status.

- **Search tasks with filters**  
  `GET /api/tasks/filter?title={title}&status={status}&dueDate={dueDate}`  
  Accessible by both users and admins. Filters tasks based on title, status, and due date (optional).

### Authentication

- **Login**  
  `POST /auth/login`  
  Accepts a `LoginRequestDTO` with `username` and `password`. Returns a JWT token if credentials are valid.

## Technologies Used

- **Spring Boot**: Backend framework
- **Spring Security**: Authentication and authorization
- **JWT**: JSON Web Tokens for secure login
- **H2 Database**: In-memory database for development purposes (can be switched to another database like MySQL)
- **BCrypt**: For hashing passwords

## Setup

### Prerequisites

1. Java 11 or newer
2. Maven or Gradle (depending on the project configuration)
3. An IDE (IntelliJ IDEA, Eclipse, etc.)

### Clone the repository

```bash
git clone https://github.com/mohamedramadanmohamed/taskmanagement.git