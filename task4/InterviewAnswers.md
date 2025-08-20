# Interview Questions & Answers - Java File I/O & CSV Processing

## 1. What is BufferedReader in Java?

`BufferedReader` is a Java class that provides buffered reading of text from a character input stream. It extends the `Reader` class and improves reading performance by buffering characters, reducing the number of system calls needed to read data.

**Key characteristics:**
- **Buffered Reading**: Reads characters into a buffer for efficient access
- **Line-by-Line Reading**: Provides `readLine()` method for convenient line reading
- **Performance**: Significantly faster than reading character by character
- **Memory Efficient**: Reduces I/O operations by buffering data

**Example from our CSV system:**
```java
try (BufferedReader reader = new BufferedReader(new FileReader("products.csv"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        // Process each line
        Product product = parseProductLine(line);
        if (product != null) {
            products.add(product);
        }
    }
}
```

**Benefits of BufferedReader:**
- **Efficiency**: Reduces system calls by buffering data
- **Convenience**: `readLine()` method for easy line processing
- **Performance**: Much faster than `FileReader` alone
- **Memory Management**: Automatic buffer management

**When to use:**
- Reading text files line by line
- Processing large files efficiently
- When you need buffered character input
- CSV file processing (as in our example)

## 2. How do you read a file line by line?

There are several ways to read a file line by line in Java:

**Method 1: Using BufferedReader (Recommended)**
```java
try (BufferedReader reader = new BufferedReader(new FileReader("filename.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        // Process the line
        System.out.println(line);
    }
}
```

**Method 2: Using Files.readAllLines() (Java 7+)**
```java
try {
    List<String> lines = Files.readAllLines(Paths.get("filename.txt"));
    for (String line : lines) {
        // Process the line
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

**Method 3: Using Scanner**
```java
try (Scanner scanner = new Scanner(new File("filename.txt"))) {
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        // Process the line
        System.out.println(line);
    }
}
```

**Method 4: Using Stream API (Java 8+)**
```java
try (Stream<String> lines = Files.lines(Paths.get("filename.txt"))) {
    lines.forEach(line -> {
        // Process the line
        System.out.println(line);
    });
} catch (IOException e) {
    e.printStackTrace();
}
```

**Example from our system:**
```java
private List<Product> readCSVFile() throws IOException {
    List<Product> products = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
        String line;
        boolean isFirstLine = true;
        
        // Read file line by line
        while ((line = reader.readLine()) != null) {
            // Skip header line
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            
            // Skip empty lines
            if (line.trim().isEmpty()) {
                continue;
            }
            
            // Parse the line
            Product product = parseProductLine(line);
            if (product != null) {
                products.add(product);
            }
        }
    }
    
    return products;
}
```

**Best practices:**
- Always use try-with-resources for automatic resource cleanup
- Handle IOException appropriately
- Consider file encoding if dealing with international characters
- Use BufferedReader for large files due to performance benefits

## 3. What is the difference between FileReader and BufferedReader?

Both classes are used for reading character data, but they serve different purposes and have different characteristics:

**FileReader:**
- **Direct Reading**: Reads characters directly from the file
- **No Buffering**: Each read operation results in a system call
- **Performance**: Slower for multiple read operations
- **Memory**: Lower memory usage
- **Use Case**: Simple character reading, small files

**BufferedReader:**
- **Buffered Reading**: Reads characters into a buffer first
- **Efficient Access**: Subsequent reads come from the buffer
- **Performance**: Much faster for multiple read operations
- **Memory**: Higher memory usage due to buffering
- **Use Case**: Reading large files, line-by-line processing

**Performance comparison:**
```java
// FileReader - slower for multiple reads
FileReader fileReader = new FileReader("largefile.txt");
int character;
while ((character = fileReader.read()) != -1) {
    // Each read() call is a system call
    processCharacter((char) character);
}

// BufferedReader - faster for multiple reads
BufferedReader bufferedReader = new BufferedReader(new FileReader("largefile.txt"));
String line;
while ((line = bufferedReader.readLine()) != null) {
    // Multiple characters read at once, buffered
    processLine(line);
}
```

**Example from our system:**
```java
// We use BufferedReader for efficient line-by-line reading
try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
    String line;
    while ((line = reader.readLine()) != null) {
        // Each readLine() call is efficient due to buffering
        Product product = parseProductLine(line);
        if (product != null) {
            products.add(product);
        }
    }
}
```

**When to use which:**
- **FileReader**: Simple character reading, small files, when you need character-by-character control
- **BufferedReader**: Line-by-line reading, large files, when performance matters
- **General rule**: Use BufferedReader unless you specifically need FileReader's direct access

## 4. How do you write data to a file in Java? What is FileWriter?

`FileWriter` is a Java class that provides convenient methods for writing character data to files. It extends `OutputStreamWriter` and is designed for writing text files.

**Key characteristics:**
- **Character Writing**: Writes characters and strings to files
- **File Creation**: Automatically creates files if they don't exist
- **Append Mode**: Can append to existing files
- **Character Encoding**: Uses default system encoding

**Writing methods:**
```java
FileWriter writer = new FileWriter("filename.txt");

// Write a single character
writer.write('A');

// Write a string
writer.write("Hello World");

// Write a portion of a string
writer.write("Hello World", 0, 5); // Writes "Hello"

// Write with newline
writer.write("Line 1\n");
writer.write("Line 2\n");

writer.close();
```

**Example from our CSV system:**
```java
private void writeFilteredProductsToCSV(List<Product> products) throws IOException {
    try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
        // Write CSV header
        writer.write("Name,Price\n");
        
        // Write each product
        for (Product product : products) {
            writer.write(product.getName() + "," + product.getPrice() + "\n");
        }
    }
}
```

**Different writing approaches:**

**Method 1: FileWriter (Simple text writing)**
```java
try (FileWriter writer = new FileWriter("output.txt")) {
    writer.write("Data to write\n");
    writer.write("More data\n");
}
```

**Method 2: BufferedWriter (Buffered writing for performance)**
```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
    writer.write("Data to write");
    writer.newLine(); // Platform-independent newline
    writer.write("More data");
}
```

**Method 3: PrintWriter (Formatted writing)**
```java
try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
    writer.println("Data to write");
    writer.printf("Formatted data: %.2f", 123.456);
}
```

**Method 4: Files.write() (Java 7+)**
```java
List<String> lines = Arrays.asList("Line 1", "Line 2", "Line 3");
Files.write(Paths.get("output.txt"), lines);
```

**Best practices:**
- Always use try-with-resources for automatic cleanup
- Handle IOException appropriately
- Consider using BufferedWriter for multiple writes
- Use appropriate character encoding if needed
- Close the writer when done

## 5. How do you handle exceptions in file operations?

File operations in Java can throw various exceptions, and proper exception handling is crucial for robust applications.

**Common file operation exceptions:**
- **FileNotFoundException**: File doesn't exist or can't be accessed
- **IOException**: General I/O errors (disk full, permission denied, etc.)
- **SecurityException**: Insufficient permissions
- **NumberFormatException**: When parsing numeric data from files

**Exception handling strategies:**

**Strategy 1: Try-catch with specific exception types**
```java
try {
    BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
    // File operations
    reader.close();
} catch (FileNotFoundException e) {
    System.err.println("File not found: " + e.getMessage());
    // Handle missing file
} catch (IOException e) {
    System.err.println("I/O error: " + e.getMessage());
    // Handle I/O errors
} catch (SecurityException e) {
    System.err.println("Permission denied: " + e.getMessage());
    // Handle permission issues
}
```

**Strategy 2: Try-with-resources (Recommended)**
```java
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    // File operations - resources automatically closed
    String line = reader.readLine();
    // Process line
} catch (FileNotFoundException e) {
    System.err.println("File not found: " + e.getMessage());
} catch (IOException e) {
    System.err.println("I/O error: " + e.getMessage());
}
```

**Strategy 3: Propagating exceptions**
```java
public void processFile() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
        // File operations
    }
    // IOException is automatically propagated
}
```

**Example from our system:**
```java
public static void main(String[] args) {
    CSVProcessor processor = new CSVProcessor();
    
    try {
        // Step 1: Create sample products.csv file
        processor.createSampleCSV();
        System.out.println("✅ Sample CSV file created successfully!");
        
        // Step 2: Read and process the CSV file
        List<Product> allProducts = processor.readCSVFile();
        System.out.println("✅ CSV file read successfully!");
        
        // ... more operations
        
    } catch (IOException e) {
        System.err.println("❌ Error during file processing: " + e.getMessage());
        e.printStackTrace();
    }
}
```

**Error handling in parsing:**
```java
private Product parseProductLine(String line) {
    try {
        String[] parts = line.split(",");
        
        if (parts.length >= 2) {
            String name = parts[0].trim();
            double price = Double.parseDouble(parts[1].trim());
            
            if (!name.isEmpty() && price >= 0) {
                return new Product(name, price);
            }
        }
    } catch (NumberFormatException e) {
        System.err.println("⚠️  Warning: Could not parse price in line: " + line);
        // Continue processing other lines instead of failing completely
    }
    
    return null;
}
```

**Best practices:**
- Use try-with-resources for automatic resource cleanup
- Catch specific exceptions when possible
- Provide meaningful error messages
- Log errors appropriately
- Consider recovery strategies
- Don't ignore exceptions

## 6. What is parsing in Java?

Parsing in Java is the process of analyzing a string or data stream to extract meaningful information or convert it into structured data types. It involves breaking down input data according to specific rules or formats.

**Types of parsing:**

**1. String Parsing**
```java
// Parse comma-separated values
String csvLine = "Laptop,1299.99";
String[] parts = csvLine.split(",");
String name = parts[0];        // "Laptop"
double price = Double.parseDouble(parts[1]); // 1299.99
```

**2. Numeric Parsing**
```java
// Parse different numeric types
String intStr = "123";
String doubleStr = "123.45";

int intValue = Integer.parseInt(intStr);           // 123
double doubleValue = Double.parseDouble(doubleStr); // 123.45
long longValue = Long.parseLong("999999999");      // 999999999
```

**3. Date Parsing**
```java
// Parse date strings
String dateStr = "2023-12-25";
LocalDate date = LocalDate.parse(dateStr); // 2023-12-25
```

**Example from our CSV system:**
```java
private Product parseProductLine(String line) {
    try {
        // Split line by comma to separate fields
        String[] parts = line.split(",");
        
        // Check if we have both name and price
        if (parts.length >= 2) {
            String name = parts[0].trim();           // Extract and clean name
            double price = Double.parseDouble(parts[1].trim()); // Parse price
            
            // Validate parsed data
            if (!name.isEmpty() && price >= 0) {
                return new Product(name, price);
            }
        }
    } catch (NumberFormatException e) {
        System.err.println("⚠️  Warning: Could not parse price in line: " + line);
    }
    
    return null;
}
```

**Common parsing techniques:**

**1. String Splitting**
```java
// Split by delimiter
String data = "John,Doe,30,Engineer";
String[] fields = data.split(",");

// Split with limit
String[] limited = data.split(",", 2); // ["John", "Doe,30,Engineer"]

// Split by multiple delimiters
String text = "Hello;World,Test:Data";
String[] parts = text.split("[,;:]");
```

**2. Regular Expression Parsing**
```java
import java.util.regex.*;

String text = "Product: Laptop, Price: $1299.99";
Pattern pattern = Pattern.compile("Product: (.*?), Price: \\$(\\d+\\.\\d{2})");
Matcher matcher = pattern.matcher(text);

if (matcher.find()) {
    String productName = matcher.group(1);  // "Laptop"
    String price = matcher.group(2);        // "1299.99"
}
```

**3. Scanner Parsing**
```java
String input = "Laptop 1299.99 15";
Scanner scanner = new Scanner(input);

String name = scanner.next();      // "Laptop"
double price = scanner.nextDouble(); // 1299.99
int quantity = scanner.nextInt();    // 15
```

**4. JSON Parsing (with external libraries)**
```java
// Using Jackson library
ObjectMapper mapper = new ObjectMapper();
Product product = mapper.readValue(jsonString, Product.class);

// Using Gson library
Gson gson = new Gson();
Product product = gson.fromJson(jsonString, Product.class);
```

**Parsing best practices:**
- Always validate parsed data
- Handle parsing exceptions gracefully
- Use appropriate data types for parsed values
- Consider performance for large datasets
- Use try-catch blocks for parsing operations
- Provide meaningful error messages for parsing failures

## 7. How do you split a string in Java? How do you check if a file exists?

**String Splitting:**

**Method 1: split() method (Most common)**
```java
String csvLine = "Name,Price,Quantity";
String[] parts = csvLine.split(",");
// parts = ["Name", "Price", "Quantity"]

// Split with limit
String[] limited = csvLine.split(",", 2);
// limited = ["Name", "Price,Quantity"]

// Split by multiple delimiters
String text = "Hello;World,Test:Data";
String[] parts = text.split("[,;:]");
// parts = ["Hello", "World", "Test", "Data"]
```

**Method 2: StringTokenizer (Legacy)**
```java
String text = "Hello,World,Test";
StringTokenizer tokenizer = new StringTokenizer(text, ",");
while (tokenizer.hasMoreTokens()) {
    String token = tokenizer.nextToken();
    System.out.println(token);
}
```

**Method 3: Manual parsing**
```java
String text = "Name:Value:Description";
int firstColon = text.indexOf(':');
int secondColon = text.indexOf(':', firstColon + 1);

String name = text.substring(0, firstColon);
String value = text.substring(firstColon + 1, secondColon);
String description = text.substring(secondColon + 1);
```

**Example from our CSV system:**
```java
private Product parseProductLine(String line) {
    try {
        // Split line by comma to get name and price
        String[] parts = line.split(",");
        
        // Check if we have both name and price
        if (parts.length >= 2) {
            String name = parts[0].trim();           // First part is name
            double price = Double.parseDouble(parts[1].trim()); // Second part is price
            
            // Validate parsed data
            if (!name.isEmpty() && price >= 0) {
                return new Product(name, price);
            }
        }
    } catch (NumberFormatException e) {
        System.err.println("⚠️  Warning: Could not parse price in line: " + line);
    }
    
    return null;
}
```

**File Existence Checking:**

**Method 1: File.exists() (Most common)**
```java
File file = new File("products.csv");
if (file.exists()) {
    System.out.println("File exists!");
} else {
    System.out.println("File does not exist!");
}
```

**Method 2: Files.exists() (Java 7+)**
```java
import java.nio.file.*;

Path path = Paths.get("products.csv");
if (Files.exists(path)) {
    System.out.println("File exists!");
} else {
    System.out.println("File does not exist!");
}
```

**Method 3: Check with file operations**
```java
try (FileReader reader = new FileReader("products.csv")) {
    // File exists and is readable
    System.out.println("File exists and is readable!");
} catch (FileNotFoundException e) {
    // File doesn't exist or can't be accessed
    System.out.println("File not found: " + e.getMessage());
}
```

**Example from our system:**
```java
private void verifyOutputFile() throws IOException {
    File outputFile = new File(OUTPUT_FILE);
    
    if (outputFile.exists()) {
        System.out.println("✅ Output file exists: " + outputFile.getAbsolutePath());
        System.out.println("📊 File size: " + outputFile.length() + " bytes");
        
        // Read and display the output file content
        System.out.println("\n--- Output File Content ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(OUTPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    } else {
        System.err.println("❌ Output file was not created!");
    }
}
```

**Additional file checking methods:**

**Check file properties:**
```java
File file = new File("products.csv");

if (file.exists()) {
    System.out.println("File exists");
    System.out.println("Is file: " + file.isFile());
    System.out.println("Is directory: " + file.isDirectory());
    System.out.println("Can read: " + file.canRead());
    System.out.println("Can write: " + file.canWrite());
    System.out.println("File size: " + file.length() + " bytes");
    System.out.println("Last modified: " + new Date(file.lastModified()));
}
```

**Check with Path (Java 7+):**
```java
Path path = Paths.get("products.csv");

if (Files.exists(path)) {
    System.out.println("File exists");
    System.out.println("Is regular file: " + Files.isRegularFile(path));
    System.out.println("Is directory: " + Files.isDirectory(path));
    System.out.println("Is readable: " + Files.isReadable(path));
    System.out.println("Is writable: " + Files.isWritable(path));
    System.out.println("File size: " + Files.size(path) + " bytes");
}
```

**Best practices:**
- Use `File.exists()` for simple existence checks
- Use `Files.exists()` for modern Java applications
- Always handle FileNotFoundException in file operations
- Check file permissions if needed
- Consider using try-with-resources for file operations

## 8. What happens if you open a file that does not exist?

When you try to open a file that doesn't exist in Java, different behaviors occur depending on the method used:

**FileReader - Throws FileNotFoundException:**
```java
try {
    FileReader reader = new FileReader("nonexistent.txt");
    // This line will throw FileNotFoundException
} catch (FileNotFoundException e) {
    System.err.println("File not found: " + e.getMessage());
    // Output: File not found: nonexistent.txt (The system cannot find the file specified)
}
```

**FileWriter - Creates the file:**
```java
try {
    FileWriter writer = new FileWriter("newfile.txt");
    // This will create the file if it doesn't exist
    writer.write("Hello World");
    writer.close();
    System.out.println("File created and written to successfully!");
} catch (IOException e) {
    System.err.println("Error: " + e.getMessage());
}
```

**BufferedReader with FileReader:**
```java
try {
    BufferedReader reader = new BufferedReader(new FileReader("nonexistent.txt"));
    // This will throw FileNotFoundException
} catch (FileNotFoundException e) {
    System.err.println("Cannot open file: " + e.getMessage());
}
```

**Example from our system showing proper error handling:**
```java
public static void main(String[] args) {
    CSVProcessor processor = new CSVProcessor();
    
    try {
        // These operations could fail if files don't exist or can't be created
        processor.createSampleCSV();
        List<Product> allProducts = processor.readCSVFile();
        // ... more operations
        
    } catch (IOException e) {
        // Handle all I/O related errors including FileNotFoundException
        System.err.println("❌ Error during file processing: " + e.getMessage());
        e.printStackTrace();
    }
}
```

**Different scenarios and their outcomes:**

**1. Reading non-existent file:**
```java
// This will throw FileNotFoundException
FileReader reader = new FileReader("missing.txt");
```

**2. Writing to non-existent file:**
```java
// This will create the file
FileWriter writer = new FileWriter("newfile.txt");
```

**3. Checking file existence before operations:**
```java
File file = new File("products.csv");
if (file.exists()) {
    // Safe to read
    try (FileReader reader = new FileReader(file)) {
        // Read file contents
    }
} else {
    System.out.println("File does not exist, creating it...");
    // Create the file
    try (FileWriter writer = new FileWriter(file)) {
        writer.write("Name,Price\n");
    }
}
```

**4. Handling missing files gracefully:**
```java
public List<Product> readCSVFile() throws IOException {
    File inputFile = new File(INPUT_FILE);
    
    if (!inputFile.exists()) {
        System.out.println("Input file not found, creating sample data...");
        createSampleCSV();
    }
    
    List<Product> products = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
        // Read file contents
        String line;
        boolean isFirstLine = true;
        
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            
            Product product = parseProductLine(line);
            if (product != null) {
                products.add(product);
            }
        }
    }
    
    return products;
}
```

**Best practices for handling missing files:**

**1. Always use try-catch:**
```java
try {
    FileReader reader = new FileReader("file.txt");
    // File operations
} catch (FileNotFoundException e) {
    // Handle missing file
    System.err.println("File not found: " + e.getMessage());
    // Consider creating the file or using default data
}
```

**2. Check existence before operations:**
```java
File file = new File("important.txt");
if (file.exists()) {
    // Safe to proceed
} else {
    // Handle missing file case
}
```

**3. Provide meaningful error messages:**
```java
try {
    // File operations
} catch (FileNotFoundException e) {
    System.err.println("Cannot find required file: " + e.getMessage());
    System.err.println("Please ensure the file exists and is accessible.");
    System.exit(1); // Exit gracefully
}
```

**4. Use try-with-resources:**
```java
try (FileReader reader = new FileReader("file.txt")) {
    // File operations - automatically closed
} catch (FileNotFoundException e) {
    // Handle missing file
}
```

**Common error messages:**
- **FileNotFoundException**: "The system cannot find the file specified"
- **SecurityException**: "Access denied" (insufficient permissions)
- **IOException**: Various I/O related errors

**Recovery strategies:**
- Create the file if it doesn't exist
- Use default data
- Prompt user for correct file path
- Log the error and continue with alternative data
- Exit gracefully with helpful error message
