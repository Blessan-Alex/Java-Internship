# Task 9: Unit Testing with JUnit

## Overview
Comprehensive unit testing examples for Spring Boot REST API using JUnit 5 concepts and testing patterns.

## Features
- **Complete Test Coverage**: Tests for all CRUD operations
- **Success & Failure Scenarios**: Both positive and negative test cases
- **Mocking Examples**: Demonstrates dependency mocking
- **Exception Handling**: Tests error conditions
- **Simple Implementation**: No external dependencies required

## Files
- `TaskControllerTest.java` - Complete unit testing examples
- `InterviewAnswers.md` - Interview Q&A
- `README.md` - This documentation

## How to Run

### Prerequisites
- Java 17 or higher
- No external dependencies required

### Steps
1. **Compile the test:**
   ```bash
   javac TaskControllerTest.java
   ```

2. **Run the tests:**
   ```bash
   java TaskControllerTest
   ```

3. **View test results:**
   - All tests will run and show PASSED/FAILED status
   - Detailed output for each test scenario

## Test Scenarios Covered

### Success Scenarios
- ✅ **GET all tasks** - Returns list of tasks
- ✅ **GET task by ID** - Returns specific task
- ✅ **POST create task** - Creates new task
- ✅ **PUT update task** - Updates existing task
- ✅ **DELETE task** - Removes task

### Failure Scenarios
- ❌ **GET task by ID - Not Found** - Handles missing tasks
- ❌ **POST invalid data** - Validates input data
- ❌ **PUT non-existent task** - Handles update failures
- ❌ **DELETE non-existent task** - Handles delete failures
- ❌ **Repository exceptions** - Handles database errors

## Testing Concepts Demonstrated

### 1. Test Structure (AAA Pattern)
```java
// Arrange - Setup test data
MockTaskRepository mockRepository = new MockTaskRepository();
TaskController taskController = new TaskController(mockRepository);

// Act - Execute the method being tested
List<Task> result = taskController.getAllTasks();

// Assert - Verify the results
assertNotNull(result, "Result should not be null");
assertEquals(2, result.size(), "Should return 2 tasks");
```

### 2. Mocking Dependencies
```java
static class MockTaskRepository {
    private List<Task> tasks = new ArrayList<>();
    
    public Task save(Task task) {
        // Simulate database save operation
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }
}
```

### 3. Exception Testing
```java
try {
    taskController.createTask(invalidTask);
    fail("Should throw IllegalArgumentException");
} catch (IllegalArgumentException e) {
    assertEquals("Task title cannot be empty", e.getMessage());
}
```

### 4. Custom Assertions
```java
private void assertEquals(Object expected, Object actual, String message) {
    if (!expected.equals(actual)) {
        throw new AssertionError(message + " - Expected: " + expected + ", Actual: " + actual);
    }
}
```

## Key Testing Principles

### 1. Isolation
- Each test is independent
- No shared state between tests
- Mocked dependencies

### 2. Completeness
- Test both success and failure paths
- Cover edge cases and exceptions
- Validate all return values

### 3. Readability
- Clear test method names
- Descriptive assertion messages
- Organized test structure

### 4. Maintainability
- Reusable test utilities
- Consistent patterns
- Easy to understand and modify

## Learning Outcomes
- Understanding unit testing fundamentals
- JUnit 5 testing patterns and annotations
- Mocking and dependency injection in tests
- Testing REST API endpoints
- Exception handling in tests
- Test coverage and quality assurance
- Best practices for writing maintainable tests

## Real-World Application
This example demonstrates testing patterns that can be applied to:
- Spring Boot REST APIs
- Service layer methods
- Repository layer operations
- Business logic validation
- Error handling scenarios
