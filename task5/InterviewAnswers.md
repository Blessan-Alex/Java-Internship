# Interview Questions & Answers - Java Exception Handling

## 1. What is exception handling in Java?
Exception handling is a mechanism to handle runtime errors gracefully. It prevents program crashes and allows recovery from errors.

**Key concepts:**
- **Try-catch blocks**: Catch and handle exceptions
- **Exception hierarchy**: Checked vs unchecked exceptions
- **Error recovery**: Continue execution despite errors

## 2. What are checked and unchecked exceptions?
**Checked Exceptions (Compile-time):**
- Must be handled or declared with `throws`
- Extend `Exception` class
- Examples: `IOException`, `FileNotFoundException`

**Unchecked Exceptions (Runtime):**
- Don't require explicit handling
- Extend `RuntimeException`
- Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`

## 3. How does try-catch-finally work?
```java
try {
    // Code that might throw exception
} catch (ExceptionType e) {
    // Handle specific exception
} finally {
    // Always executes (cleanup code)
}
```

**Purpose:**
- **Try**: Contains risky code
- **Catch**: Handles specific exceptions
- **Finally**: Ensures cleanup (always runs)

## 4. What is the difference between throw and throws?
**`throw`**: Throws an exception explicitly
```java
throw new InvalidProductDataException("Invalid data");
```

**`throws`**: Declares that method may throw exception
```java
public void processData() throws IOException
```

## 5. How do you create a custom exception?
```java
public class InvalidProductDataException extends Exception {
    public InvalidProductDataException(String message) {
        super(message);
    }
}
```

**Steps:**
1. Extend `Exception` (checked) or `RuntimeException` (unchecked)
2. Provide constructors
3. Use `throw` to throw it

## 6. What happens if you don't handle an exception?
- **Unchecked exceptions**: Program crashes with stack trace
- **Checked exceptions**: Compilation error
- **Default behavior**: JVM handles uncaught exceptions

## 7. Why is finally block used?
**Purpose:**
- **Resource cleanup**: Close files, connections
- **Guaranteed execution**: Always runs regardless of exceptions
- **Essential operations**: Must happen even if errors occur

## 8. What is NumberFormatException?
Runtime exception thrown when trying to parse invalid numeric strings.

**Example:**
```java
try {
    int number = Integer.parseInt("abc"); // Throws NumberFormatException
} catch (NumberFormatException e) {
    // Handle parsing error
}
```

## 9. How do you close resources in Java safely?
**Try-with-resources (Recommended):**
```java
try (FileReader reader = new FileReader("file.txt")) {
    // Use resource
} // Automatically closed
```

**Manual cleanup in finally:**
```java
FileReader reader = null;
try {
    reader = new FileReader("file.txt");
} finally {
    if (reader != null) reader.close();
}
```

## 10. What is the purpose of multiple catch blocks?
Handle different exception types with specific logic:

```java
try {
    // Risky code
} catch (FileNotFoundException e) {
    // Handle missing file
} catch (IOException e) {
    // Handle I/O errors
} catch (Exception e) {
    // Handle any other exceptions
}
```

**Benefits:**
- **Specific handling**: Different logic for different errors
- **Better error messages**: Tailored responses
- **Recovery strategies**: Appropriate actions per error type
