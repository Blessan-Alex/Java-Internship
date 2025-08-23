# Task 9: Unit Testing with JUnit - Interview Answers

## Interview Questions and Answers

### 1. What is unit testing?
**Answer:** Unit testing is a software testing method where individual units/components of code are tested in isolation to ensure they work correctly. Each test focuses on a single function, method, or class.

### 2. What is JUnit in Java?
**Answer:** JUnit is a popular testing framework for Java that provides annotations and assertions to write and run unit tests. It helps automate the testing process and provides detailed test reports.

### 3. Difference between unit test and integration test?
**Answer:** 
- **Unit Test**: Tests individual components in isolation (e.g., single method)
- **Integration Test**: Tests how multiple components work together (e.g., API endpoints with database)

### 4. What is the use of @Test annotation?
**Answer:** The @Test annotation marks a method as a test case. JUnit will automatically execute methods with this annotation when running tests.

### 5. How do you test REST APIs in Spring Boot?
**Answer:** Use @WebMvcTest with MockMvc to simulate HTTP requests, mock dependencies with @MockBean, and assert response status, content, and JSON structure.

### 6. What is MockMvc in Spring Boot testing?
**Answer:** MockMvc is a testing utility that simulates HTTP requests to your controllers without starting a full web server. It allows testing REST endpoints in isolation.

### 7. How do you test for exceptions in JUnit?
**Answer:** Use assertThrows() method or try-catch blocks to verify that specific exceptions are thrown when expected error conditions occur.

### 8. What is mocking and why do we use it?
**Answer:** Mocking creates fake objects that simulate real dependencies. We use it to isolate the unit being tested and control the behavior of external dependencies like databases or services.

### 9. How do you write test cases for failure scenarios?
**Answer:** Test edge cases, invalid inputs, null values, empty data, and exception conditions. Use assertThrows() for exceptions and verify error responses for API failures.

### 10. How do you check test coverage in Java?
**Answer:** Use tools like JaCoCo, Cobertura, or IDE plugins to measure code coverage. They show which lines of code are executed during tests and identify untested areas.
