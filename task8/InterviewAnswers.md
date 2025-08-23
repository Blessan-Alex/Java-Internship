# Task 8: Frontend Integration with REST API - Interview Answers

## Interview Questions and Answers

### 1. What is Fetch API in JavaScript?
**Answer:** Fetch API is a modern JavaScript interface for making HTTP requests. It provides a simple way to fetch resources asynchronously across the network, replacing the older XMLHttpRequest.

### 2. How do you send data to a REST API using JavaScript?
**Answer:** Use fetch() with POST method, set Content-Type header to 'application/json', and send data in the body as JSON string using JSON.stringify().

### 3. What is the difference between JSON and JavaScript objects?
**Answer:** 
- **JavaScript objects**: Native data structure with properties and methods
- **JSON**: String format for data exchange, must be parsed to become JavaScript objects

### 4. What is DOM manipulation?
**Answer:** DOM manipulation is the process of dynamically changing HTML elements, their content, attributes, and structure using JavaScript without reloading the page.

### 5. How do you handle asynchronous requests in JavaScript?
**Answer:** Use async/await with promises, or .then()/.catch() methods to handle asynchronous operations and their results.

### 6. What is the difference between GET and POST methods?
**Answer:** 
- **GET**: Retrieves data, parameters in URL, no request body
- **POST**: Sends data to server, data in request body, used for creating/updating

### 7. How do you handle API errors in JavaScript?
**Answer:** Use try-catch blocks with async/await, or .catch() with promises, check response.ok status, and display user-friendly error messages.

### 8. What is CORS and how do you handle it in frontend-backend integration?
**Answer:** CORS (Cross-Origin Resource Sharing) is a security policy. Handle it by configuring the backend to allow specific origins or using proxy servers.

### 9. What are promises in JavaScript?
**Answer:** Promises represent the eventual completion of an asynchronous operation. They have three states: pending, fulfilled, or rejected, and provide .then() and .catch() methods.

### 10. How do you update the UI dynamically without reloading the page?
**Answer:** Use DOM manipulation methods like innerHTML, createElement, appendChild to modify HTML elements in real-time based on API responses or user actions.
