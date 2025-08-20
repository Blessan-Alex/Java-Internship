# Task 6: JDBC & Database Integration

## Overview
Demonstrates Java database connectivity using JDBC with H2 in-memory database. Shows basic CRUD operations and SQL injection prevention.

## Files
- `User.java` - Simple User class with id, name, email
- `UserDatabaseManager.java` - Main JDBC application
- `TestJDBC.java` - Test class for JDBC functionality
- `InterviewAnswers.md` - Brief answers to interview questions

## How to Run
```bash
javac *.java
java TestJDBC
java UserDatabaseManager
```

## Key Features
- **Database Connection**: H2 in-memory database
- **CRUD Operations**: Create, Read, Update, Delete users
- **PreparedStatement**: SQL injection prevention
- **Resource Management**: Proper connection handling
- **Error Handling**: SQLException handling

## JDBC Operations Demonstrated
1. **Connect**: Establish database connection
2. **Create**: Build users table
3. **Insert**: Add sample users
4. **Select**: Retrieve and display users
5. **Update**: Modify user information
6. **Delete**: Remove users
7. **Cleanup**: Close database resources

## Database Schema
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);
```
