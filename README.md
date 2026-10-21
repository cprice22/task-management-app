# Task Management App

A task management application built while following Devtiro’s Spring Boot tutorial. This project focuses on implementing a Java backend and connecting it to the tutorial-provided React frontend.

## Features

- Create, view, update, and delete tasks
- Assign high, medium, or low priority
- Track open and completed tasks
- Add optional descriptions and due dates
- Validate incoming requests
- Handle API errors through a global exception handler

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA and Hibernate
- H2 Database
- Maven
- React and TypeScript (tutorial-provided frontend)
- Docker Compose

## Run Locally

### Prerequisites

- JDK 17
- Docker Desktop running with Docker Compose available

### Start the backend

From the project root, run:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend runs at http://localhost:8080.

### Start the frontend

Open a second terminal in the project root and run:

```powershell
docker compose up --build
```

Open http://localhost:3000.

Keep both processes running while using the app. Press Ctrl+C in each terminal to stop them.

## Architecture

- **Controllers:** Handle HTTP requests and responses
- **DTOs and mappers:** Define and convert API data
- **Services:** Implement task management logic
- **Repositories:** Provide database operations
- **Entities:** Map task data to the database

## Credits

Built following [Devtiro’s task app tutorial](https://github.com/devtiro/devtiro-task-app).

The React frontend was provided by the tutorial and customized for this project. Its license is retained in `frontend/LICENSE`.
