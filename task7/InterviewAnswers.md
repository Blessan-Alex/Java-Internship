# Task 7: REST API with Spring Boot - Interview Answers

## Interview Questions and Answers

### 1. What is REST API?
**Answer:** REST (Representational State Transfer) API is an architectural style for designing networked applications. It uses HTTP methods to perform CRUD operations on resources, treating everything as a resource with a unique URI.

### 2. What are HTTP methods used in REST?
**Answer:** 
- **GET**: Retrieve data (read)
- **POST**: Create new data
- **PUT**: Update existing data
- **DELETE**: Remove data
- **PATCH**: Partial update

### 3. Difference between @RestController and @Controller?
**Answer:** 
- **@Controller**: Traditional Spring MVC controller, requires @ResponseBody for JSON responses
- **@RestController**: Combines @Controller + @ResponseBody, automatically serializes return values to JSON

### 4. How does Spring Boot simplify REST API creation?
**Answer:** Spring Boot provides auto-configuration, embedded servers, starter dependencies, and eliminates boilerplate code. Just add @SpringBootApplication and @RestController annotations.

### 5. What is JSON serialization in Java?
**Answer:** Converting Java objects to JSON format (and vice versa) for data exchange. Spring Boot uses Jackson library by default for automatic serialization/deserialization.

### 6. How do you test REST APIs?
**Answer:** 
- **Postman**: Manual API testing tool
- **Unit Tests**: @SpringBootTest with TestRestTemplate
- **Integration Tests**: @WebMvcTest
- **H2 Console**: Database inspection

### 7. What is the role of @RequestBody and @ResponseBody?
**Answer:** 
- **@RequestBody**: Converts incoming JSON to Java object
- **@ResponseBody**: Converts Java object to JSON response (automatically handled by @RestController)

### 8. What is the default JSON converter in Spring Boot?
**Answer:** Jackson (ObjectMapper) is the default JSON converter. It automatically handles conversion between Java objects and JSON.

### 9. What is H2 Database and why is it used in Spring Boot?
**Answer:** H2 is an in-memory, lightweight database perfect for development and testing. It requires no setup, starts automatically, and provides a web console for data inspection.

### 10. How do you handle errors in REST APIs?
**Answer:** 
- Use appropriate HTTP status codes (404, 500, etc.)
- Return ResponseEntity with proper status
- Implement @ControllerAdvice for global exception handling
- Use try-catch blocks in controllers
