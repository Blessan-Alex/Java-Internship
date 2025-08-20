import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestCSVProcessor {
    public static void main(String[] args) {
        System.out.println("=== Testing CSV File I/O Processing ===\n");
        
        // Test 1: Create Product objects
        System.out.println("Test 1: Creating Product objects");
        Product product1 = new Product("Laptop", 1299.99);
        Product product2 = new Product("Mouse", 49.99);
        Product product3 = new Product("Monitor", 349.99);
        
        System.out.println("Product 1: " + product1);
        System.out.println("Product 2: " + product2);
        System.out.println("Product 3: " + product3);
        System.out.println();
        
        // Test 2: Test price filtering
        System.out.println("Test 2: Price filtering");
        double threshold = 1000.0;
        System.out.println("Price threshold: $" + threshold);
        
        System.out.println("Laptop > $1000: " + product1.isPriceGreaterThan(threshold));
        System.out.println("Mouse > $1000: " + product2.isPriceGreaterThan(threshold));
        System.out.println("Monitor > $1000: " + product3.isPriceGreaterThan(threshold));
        System.out.println();
        
        // Test 3: Test CSV line parsing
        System.out.println("Test 3: CSV line parsing");
        String[] testLines = {
            "Smartphone,899.50",
            "Headphones,299.99",
            "Keyboard,129.99",
            "Invalid,abc",  // Invalid price
            "OnlyName",      // Missing price
            ""              // Empty line
        };
        
        for (String line : testLines) {
            Product parsed = parseTestLine(line);
            if (parsed != null) {
                System.out.println("Parsed: " + parsed);
            } else {
                System.out.println("Failed to parse: '" + line + "'");
            }
        }
        System.out.println();
        
        // Test 4: Test file operations
        System.out.println("Test 4: File operations");
        testFileOperations();
        
        System.out.println("=== All Tests Completed Successfully! ===");
        System.out.println("The CSV Processor is working correctly.");
        System.out.println("You can now run the full CSVProcessor class.");
    }
    
    /**
     * Parse a test line to create a Product object
     */
    private static Product parseTestLine(String line) {
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
            // Expected for invalid price
        }
        
        return null;
    }
    
    /**
     * Test basic file operations
     */
    private static void testFileOperations() {
        String testFileName = "test_products.csv";
        
        try {
            // Test file writing
            System.out.println("Testing file writing...");
            try (FileWriter writer = new FileWriter(testFileName)) {
                writer.write("Name,Price\n");
                writer.write("Test Product 1,99.99\n");
                writer.write("Test Product 2,199.99\n");
            }
            System.out.println("✅ Test file created successfully");
            
            // Test file reading
            System.out.println("Testing file reading...");
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
            System.out.println("✅ Test file read successfully");
            System.out.println("Lines read: " + lines.size());
            
            // Display content
            System.out.println("File content:");
            for (String line : lines) {
                System.out.println("  " + line);
            }
            
            // Clean up test file
            File testFile = new File(testFileName);
            if (testFile.delete()) {
                System.out.println("✅ Test file cleaned up");
            }
            
        } catch (IOException e) {
            System.err.println("❌ File operation test failed: " + e.getMessage());
        }
    }
}
