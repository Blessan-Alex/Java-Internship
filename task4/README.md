# Task 4: CSV File I/O Processing

## Overview
This Java application demonstrates File I/O operations by implementing a CSV processing system that reads product data from a file, filters products by price, and writes the results to a new CSV file. The system showcases BufferedReader, FileWriter, string parsing, and exception handling.

## Learning Objectives
- Understand Java File I/O operations and stream handling
- Master BufferedReader for efficient file reading
- Implement FileWriter for file creation and writing
- Learn CSV parsing and data filtering techniques
- Handle exceptions in file operations gracefully
- Process data with conditional logic

## Files
- `Product.java` - Product class with name and price fields
- `CSVProcessor.java` - Main application demonstrating CSV I/O operations
- `TestCSVProcessor.java` - Automated testing class
- `InterviewAnswers.md` - Comprehensive answers to all interview questions
- `README.md` - This file with instructions

## How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your system
- Command line terminal or IDE

### Compilation
```bash
javac *.java
```

### Running the Main Application
```bash
java CSVProcessor
```

### Running the Test Suite
```bash
java TestCSVProcessor
```

### Using an IDE
1. Open the project in IntelliJ IDEA, Eclipse, or VS Code
2. Run the `CSVProcessor` class for full demonstration
3. Run the `TestCSVProcessor` class for component testing

## Program Features

### Core Functionality
- **CSV File Creation**: Generate sample product data in CSV format
- **File Reading**: Use BufferedReader to read CSV files line by line
- **Data Parsing**: Split CSV lines and convert data types
- **Data Filtering**: Filter products by price threshold (> $1000)
- **File Writing**: Create new CSV file with filtered results
- **File Verification**: Verify output file creation and content

### File I/O Operations Demonstrated
1. **File Creation**: Generate `products.csv` with sample data
2. **File Reading**: Read CSV using BufferedReader and FileReader
3. **Data Processing**: Parse CSV lines and create Product objects
4. **Data Filtering**: Apply price threshold filtering logic
5. **File Writing**: Write filtered data to `expensive_products.csv`
6. **File Verification**: Confirm output file creation and content

### Advanced Features
- **Exception Handling**: Comprehensive error handling for file operations
- **Data Validation**: Validate parsed data before processing
- **Resource Management**: Use try-with-resources for automatic cleanup
- **Formatted Output**: Professional display with proper formatting
- **Error Recovery**: Continue processing despite parsing errors

## Usage Examples

### Main Application Output
```
=== CSV File I/O Processing ===
Reading from: products.csv
Writing to: expensive_products.csv
Price threshold: $1000.0

📁 Sample CSV file created with 15 products
✅ Sample CSV file created successfully!
✅ CSV file read successfully!
Total products found: 15

--- All Products ---
Name                 | Price
----------------------------------------
Name: Laptop           | Price: $1,299.99
Name: Smartphone       | Price: $  899.50
Name: Headphones       | Price: $  299.99
...

--- Products with Price > $1000.0 ---
Name                 | Price
----------------------------------------
Name: Laptop           | Price: $1,299.99

📝 Wrote 1 products to expensive_products.csv
✅ Filtered products written to expensive_products.csv successfully!
✅ Output file exists: C:\...\expensive_products.csv
📊 File size: 45 bytes

--- Output File Content ---
Name,Price
Laptop,1299.99

🎉 All operations completed successfully!
```

### Generated Files
- **`products.csv`**: Input file with 15 sample products
- **`expensive_products.csv`**: Output file with products > $1000

## Testing

### Automated Tests
The `TestCSVProcessor` class demonstrates:
- Product object creation and manipulation
- Price filtering logic validation
- CSV line parsing functionality
- Basic file I/O operations
- Error handling for invalid data

### Manual Testing
Use the interactive `CSVProcessor` to:
- View CSV file creation process
- Observe file reading and parsing
- Analyze data filtering results
- Verify output file generation
- Check file content and properties

## Key Java Concepts Covered

### File I/O Operations
- **BufferedReader**: Efficient line-by-line file reading
- **FileReader**: Character-based file input
- **FileWriter**: Character-based file output
- **File Class**: File existence and property checking
- **Try-with-Resources**: Automatic resource management

### Data Processing
- **String Parsing**: Split and parse CSV data
- **Data Conversion**: Convert strings to numeric types
- **Data Validation**: Validate parsed data integrity
- **Conditional Logic**: Filter data based on criteria
- **Exception Handling**: Graceful error handling

### Advanced Java Features
- **Exception Hierarchy**: Understanding checked vs unchecked exceptions
- **Resource Management**: Proper file stream handling
- **Data Structures**: ArrayList for data collection
- **String Manipulation**: CSV line processing
- **File Operations**: File creation, reading, and writing

## Interview Questions Addressed

The system demonstrates answers to all 8 interview questions:
1. **BufferedReader in Java**: Buffered character input stream for efficient reading
2. **Line-by-Line Reading**: Multiple approaches to read files line by line
3. **FileReader vs BufferedReader**: Performance and functionality differences
4. **FileWriter**: Character-based file writing capabilities
5. **Exception Handling**: Comprehensive error handling strategies
6. **Parsing**: String parsing and data conversion techniques
7. **String Splitting**: Multiple methods to split strings and check file existence
8. **File Existence**: Behavior when opening non-existent files

## Best Practices Demonstrated

### Code Quality
- **Exception Handling**: Comprehensive error handling with try-catch blocks
- **Resource Management**: Automatic cleanup with try-with-resources
- **Data Validation**: Validate parsed data before processing
- **Error Recovery**: Continue processing despite individual line errors
- **Clean Code**: Clear method names and logical structure

### File I/O Best Practices
- **Buffered Reading**: Use BufferedReader for performance
- **Proper Cleanup**: Always close file streams
- **Exception Handling**: Handle FileNotFoundException and IOException
- **File Verification**: Check file existence and properties
- **Data Integrity**: Validate data during parsing

### Performance Considerations
- **Buffered Operations**: Efficient file reading and writing
- **Memory Management**: Process data line by line for large files
- **Resource Cleanup**: Automatic resource management
- **Error Handling**: Fail gracefully without losing data
- **Data Processing**: Efficient parsing and filtering

## Extensions and Improvements

### Possible Enhancements
- **Database Integration**: Store processed data in database
- **GUI Interface**: Swing or JavaFX for user interaction
- **Advanced Filtering**: Multiple filter criteria and sorting
- **File Formats**: Support for XML, JSON, or other formats
- **Performance Metrics**: File processing timing analysis
- **Data Validation**: Enhanced data integrity checks

### Learning Path
This project provides a foundation for:
- Advanced file processing (binary files, network I/O)
- Database programming (JDBC, JPA)
- Web development (file uploads, data processing)
- Enterprise Java (Spring Batch, data pipelines)
- Data analysis and reporting

## Troubleshooting

### Common Issues
- **File permissions**: Ensure write access to current directory
- **File encoding**: Check for character encoding issues
- **Path problems**: Verify file paths and working directory
- **Memory issues**: Large files may require streaming approach
- **Exception handling**: Ensure proper exception handling

### Debugging Tips
- Use the test class to verify individual components
- Check file permissions and directory access
- Verify CSV format and data integrity
- Monitor file creation and modification
- Use IDE debugging tools for step-by-step execution

## Performance Analysis

### File Processing Complexity
- **Time Complexity**: O(n) for reading and processing files
- **Space Complexity**: O(n) for storing product data in memory
- **Memory Usage**: Efficient with line-by-line processing
- **Scalability**: Handles large files with streaming approach

### Optimization Strategies
- **Buffered I/O**: Use BufferedReader and BufferedWriter
- **Streaming**: Process data line by line for large files
- **Resource Management**: Proper cleanup and resource handling
- **Error Handling**: Efficient error recovery and logging
- **Data Validation**: Early validation to prevent processing errors

## File Structure

### Input File Format (`products.csv`)
```csv
Name,Price
Laptop,1299.99
Smartphone,899.50
Headphones,299.99
Mouse,49.99
Keyboard,129.99
...
```

### Output File Format (`expensive_products.csv`)
```csv
Name,Price
Laptop,1299.99
```

### File Processing Flow
1. **Create Sample Data**: Generate `products.csv` with 15 products
2. **Read Input File**: Use BufferedReader to read CSV line by line
3. **Parse Data**: Split lines by comma and convert to Product objects
4. **Filter Data**: Select products with price > $1000
5. **Write Output**: Create `expensive_products.csv` with filtered data
6. **Verify Results**: Confirm file creation and display content

## Security Considerations

### File Access
- **Input Validation**: Validate file paths and content
- **Permission Checks**: Ensure appropriate file access permissions
- **Resource Limits**: Limit file size and processing time
- **Error Handling**: Don't expose sensitive information in error messages
- **File Cleanup**: Remove temporary files when appropriate

### Data Validation
- **Input Sanitization**: Clean and validate CSV data
- **Type Safety**: Ensure proper data type conversion
- **Range Checking**: Validate numeric values within expected ranges
- **Error Logging**: Log errors without exposing sensitive data
- **Graceful Degradation**: Handle invalid data without crashing
