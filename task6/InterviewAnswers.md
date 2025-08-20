# Interview Questions & Answers - Java JDBC

## 1. What is JDBC in Java?
JDBC (Java Database Connectivity) is an API that allows Java applications to connect to databases and execute SQL statements.

**Purpose:**
- Database connectivity
- SQL execution
- Data retrieval and manipulation

## 2. How do you connect Java with a database?
```java
Connection conn = DriverManager.getConnection(url, username, password);
```

**Steps:**
1. Load JDBC driver
2. Create connection string
3. Establish connection
4. Use connection for operations

## 3. What is a PreparedStatement and why use it?
**PreparedStatement**: Pre-compiled SQL statement with parameters.

**Benefits:**
- **Performance**: Compiled once, executed many times
- **Security**: Prevents SQL injection
- **Type safety**: Proper parameter binding

## 4. Difference between Statement and PreparedStatement?
**Statement:**
- Simple SQL execution
- No parameter support
- Vulnerable to SQL injection

**PreparedStatement:**
- Parameterized queries
- Better performance
- SQL injection protection

## 5. What is SQL injection and how to prevent it?
**SQL Injection**: Malicious SQL code inserted into queries.

**Prevention:**
- Use PreparedStatement
- Parameter binding
- Input validation

## 6. How do you execute queries in JDBC?
```java
// SELECT queries
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// INSERT/UPDATE/DELETE
int rows = stmt.executeUpdate("INSERT INTO users VALUES (...)");
```

## 7. What is ResultSet in JDBC?
**ResultSet**: Object containing query results.

**Usage:**
```java
while (rs.next()) {
    String name = rs.getString("name");
    int id = rs.getInt("id");
}
```

## 8. How do you handle exceptions in JDBC?
```java
try {
    // Database operations
} catch (SQLException e) {
    // Handle database errors
} finally {
    // Close resources
}
```

## 9. How do you close database connections properly?
**Try-with-resources (Recommended):**
```java
try (Connection conn = DriverManager.getConnection(url)) {
    // Use connection
} // Automatically closed
```

## 10. Can you use JDBC without a driver?
**No**. JDBC driver is required to connect to specific database types.

**Examples:**
- MySQL: mysql-connector-java
- H2: h2 database (embedded)
- PostgreSQL: postgresql driver
