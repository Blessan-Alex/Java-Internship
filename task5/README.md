# Task 5: Exception Handling - Robust File Processing

## Overview
Improves Task 4 by adding comprehensive exception handling to make the CSV processing robust and fault-tolerant.

## Files
- `InvalidProductDataException.java` - Custom exception class
- `Product.java` - Product class with validation
- `RobustCSVProcessor.java` - Main application with exception handling
- `TestExceptionHandling.java` - Test class for exception scenarios
- `InterviewAnswers.md` - Brief answers to interview questions

## How to Run
```bash
javac *.java
java TestExceptionHandling
java RobustCSVProcessor
```

## Key Features
- **Custom Exception**: `InvalidProductDataException` for data validation
- **Try-catch-finally**: Comprehensive error handling
- **Multiple catch blocks**: Handle different exception types
- **Resource cleanup**: Safe file handling with finally block
- **Error logging**: Track invalid data for debugging

## Exception Handling Demonstrated
1. **FileNotFoundException**: Handle missing files
2. **NumberFormatException**: Handle invalid price data
3. **Custom exceptions**: Validate product data
4. **Finally block**: Ensure resource cleanup
5. **Multiple catch blocks**: Specific error handling
