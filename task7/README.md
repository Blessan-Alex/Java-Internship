# Task 7: REST API with Spring Boot

## Overview
A simple REST API for task management that supports CRUD operations and JSON data exchange.

## Features
- **CRUD Operations**: Create, Read, Update, Delete tasks
- **REST Architecture**: Uses proper HTTP methods and status codes
- **JSON Data Exchange**: Automatic serialization/deserialization
- **H2 Database**: In-memory database for development/testing
- **Spring Boot**: Auto-configuration and embedded server

## Project Structure
```
task7/
├── src/main/java/com/example/
│   ├── TaskManagementApplication.java    # Main application class
│   ├── model/Task.java                  # Task entity
│   ├── repository/TaskRepository.java   # Data access layer
│   └── controller/TaskController.java   # REST endpoints
├── src/main/resources/
│   └── application.properties           # Configuration
├── src/test/java/com/example/
│   └── TaskControllerTest.java         # Test cases
├── pom.xml                             # Maven dependencies
└── InterviewAnswers.md                 # Interview Q&A
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| POST | `/api/tasks` | Create new task |
| PUT | `/api/tasks/{id}` | Update existing task |
| DELETE | `/api/tasks/{id}` | Delete task |

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Steps
1. **Navigate to project directory:**
   ```bash
   cd task7
   ```

2. **Build the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API:**
   - API Base URL: `http://localhost:8080/api/tasks`
   - H2 Console: `http://localhost:8080/h2-console`

## Testing

### Run Tests
```bash
mvn test
```

### Manual Testing with Postman
1. **Create Task (POST):**
   ```
   URL: http://localhost:8080/api/tasks
   Body: {"title": "Sample Task", "description": "Task description"}
   ```

2. **Get All Tasks (GET):**
   ```
   URL: http://localhost:8080/api/tasks
   ```

3. **Get Task by ID (GET):**
   ```
   URL: http://localhost:8080/api/tasks/1
   ```

4. **Update Task (PUT):**
   ```
   URL: http://localhost:8080/api/tasks/1
   Body: {"title": "Updated Task", "description": "Updated description"}
   ```

5. **Delete Task (DELETE):**
   ```
   URL: http://localhost:8080/api/tasks/1
   ```

## Key Spring Boot Annotations Used
- `@SpringBootApplication`: Main application class
- `@RestController`: REST controller with automatic JSON responses
- `@RequestMapping`: Base path for controller
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: HTTP method mappings
- `@RequestBody`: Convert JSON to Java object
- `@PathVariable`: Extract path variables
- `@Entity`: JPA entity
- `@Repository`: Data access layer

## Database
- **H2 In-Memory Database**: Automatically created and destroyed on startup/shutdown
- **JPA**: Automatic table creation and schema management
- **H2 Console**: Web-based database interface available at `/h2-console`

## Learning Outcomes
- Understanding REST API architecture
- Spring Boot auto-configuration
- JSON serialization/deserialization
- CRUD operations with JPA
- HTTP methods and status codes
- Testing REST APIs
