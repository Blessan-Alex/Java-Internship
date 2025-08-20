import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RobustCSVProcessor {
    private static final String INPUT_FILE = "products.csv";
    private static final String OUTPUT_FILE = "expensive_products.csv";
    private static final String INVALID_DATA_FILE = "invalid_products.csv";
    private static final double PRICE_THRESHOLD = 1000.0;
    
    private BufferedReader reader = null;
    private FileWriter writer = null;
    private FileWriter invalidWriter = null;
    
    /**
     * Main method to run the robust CSV processing application
     */
    public static void main(String[] args) {
        RobustCSVProcessor processor = new RobustCSVProcessor();
        
        System.out.println("=== Robust CSV File I/O Processing with Exception Handling ===");
        System.out.println("Reading from: " + INPUT_FILE);
        System.out.println("Writing to: " + OUTPUT_FILE);
        System.out.println("Invalid data log: " + INVALID_DATA_FILE);
        System.out.println("Price threshold: $" + PRICE_THRESHOLD);
        System.out.println();
        
        try {
            // Step 1: Create sample products.csv file with some invalid data
            processor.createSampleCSVWithInvalidData();
            System.out.println("✅ Sample CSV file created successfully!");
            
            // Step 2: Process the CSV file with robust exception handling
            List<Product> allProducts = processor.processCSVFileRobustly();
            System.out.println("✅ CSV file processed successfully!");
            System.out.println("Total valid products found: " + allProducts.size());
            
            // Display all valid products
            System.out.println("\n--- Valid Products ---");
            processor.displayProducts(allProducts);
            
            // Step 3: Filter products by price
            List<Product> expensiveProducts = processor.filterExpensiveProducts(allProducts);
            System.out.println("\n--- Products with Price > $" + PRICE_THRESHOLD + " ---");
            processor.displayProducts(expensiveProducts);
            
            // Step 4: Write filtered products to new CSV file
            processor.writeFilteredProductsToCSV(expensiveProducts);
            System.out.println("✅ Filtered products written to " + OUTPUT_FILE + " successfully!");
            
            // Step 5: Verify the new file
            processor.verifyOutputFile();
            
            System.out.println("\n🎉 All operations completed successfully!");
            
        } catch (Exception e) {
            System.err.println("❌ Critical error during processing: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure resources are closed
            processor.closeResources();
        }
    }
    
    /**
     * Create a sample products.csv file with some invalid data for testing
     */
    private void createSampleCSVWithInvalidData() throws IOException {
        try (FileWriter writer = new FileWriter(INPUT_FILE)) {
            // Write CSV header
            writer.write("Name,Price\n");
            
            // Write sample product data (including some invalid data)
            String[] productData = {
                "Laptop", "1299.99",           // Valid
                "Smartphone", "899.50",        // Valid
                "Headphones", "299.99",        // Valid
                "Mouse", "49.99",              // Valid
                "Keyboard", "129.99",          // Valid
                "Monitor", "349.99",           // Valid
                "Tablet", "599.99",            // Valid
                "Gaming Console", "499.99",    // Valid
                "Camera", "799.99",            // Valid
                "Speaker", "199.99",           // Valid
                "Microphone", "89.99",         // Valid
                "Webcam", "159.99",            // Valid
                "Printer", "249.99",           // Valid
                "Scanner", "179.99",           // Valid
                "External Hard Drive", "89.99", // Valid
                "", "199.99",                  // Invalid: empty name
                "Invalid Product", "abc",      // Invalid: non-numeric price
                "Negative Product", "-50.00",  // Invalid: negative price
                "Expensive Product", "2000000.00", // Invalid: too expensive
                "Null Price", "",              // Invalid: empty price
                "Only Name"                    // Invalid: missing price
            };
            
            for (int i = 0; i < productData.length; i += 2) {
                if (i + 1 < productData.length) {
                    writer.write(productData[i] + "," + productData[i + 1] + "\n");
                } else {
                    writer.write(productData[i] + "\n"); // Incomplete row
                }
            }
        }
        
        System.out.println("📁 Sample CSV file created with mixed valid/invalid data");
    }
    
    /**
     * Process CSV file with comprehensive exception handling
     * @return List of valid Product objects
     */
    private List<Product> processCSVFileRobustly() {
        List<Product> products = new ArrayList<>();
        int lineNumber = 0;
        int validProducts = 0;
        int invalidProducts = 0;
        
        try {
            // Initialize invalid data log file
            invalidWriter = new FileWriter(INVALID_DATA_FILE);
            invalidWriter.write("Line,Data,Error\n");
            
            // Open input file
            reader = new BufferedReader(new FileReader(INPUT_FILE));
            String line;
            
            // Read file line by line with exception handling
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                try {
                    // Skip header line
                    if (lineNumber == 1) {
                        continue;
                    }
                    
                    // Skip empty lines
                    if (line.trim().isEmpty()) {
                        logInvalidData(lineNumber, line, "Empty line");
                        invalidProducts++;
                        continue;
                    }
                    
                    // Parse the line with comprehensive error handling
                    Product product = parseProductLineRobustly(line, lineNumber);
                    if (product != null) {
                        products.add(product);
                        validProducts++;
                    } else {
                        invalidProducts++;
                    }
                    
                } catch (Exception e) {
                    // Log any unexpected errors
                    logInvalidData(lineNumber, line, "Unexpected error: " + e.getMessage());
                    invalidProducts++;
                }
            }
            
            System.out.println("📊 Processing Summary:");
            System.out.println("  Total lines processed: " + (lineNumber - 1));
            System.out.println("  Valid products: " + validProducts);
            System.out.println("  Invalid products: " + invalidProducts);
            System.out.println("  Success rate: " + String.format("%.1f%%", (validProducts * 100.0) / (lineNumber - 1)));
            
        } catch (FileNotFoundException e) {
            System.err.println("❌ File not found: " + e.getMessage());
            System.err.println("Please ensure " + INPUT_FILE + " exists in the current directory.");
        } catch (IOException e) {
            System.err.println("❌ I/O error during file reading: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return products;
    }
    
    /**
     * Parse a single line from CSV with robust exception handling
     * @param line CSV line to parse
     * @param lineNumber Line number for error reporting
     * @return Product object or null if parsing fails
     */
    private Product parseProductLineRobustly(String line, int lineNumber) {
        try {
            // Split line by comma
            String[] parts = line.split(",");
            
            // Check if we have both name and price
            if (parts.length < 2) {
                logInvalidData(lineNumber, line, "Insufficient data fields (expected 2, got " + parts.length + ")");
                return null;
            }
            
            String name = parts[0].trim();
            String priceStr = parts[1].trim();
            
            // Validate name
            if (name.isEmpty()) {
                logInvalidData(lineNumber, line, "Product name is empty");
                return null;
            }
            
            // Validate and parse price
            double price;
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                logInvalidData(lineNumber, line, "Invalid price format: '" + priceStr + "'");
                return null;
            }
            
            // Create product with validation (this may throw InvalidProductDataException)
            Product product = new Product(name, price);
            
            // Additional validation
            product.validate();
            
            return product;
            
        } catch (InvalidProductDataException e) {
            logInvalidData(lineNumber, line, "Data validation failed: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            logInvalidData(lineNumber, line, "Number parsing error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            logInvalidData(lineNumber, line, "General parsing error: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Log invalid data to the error log file
     * @param lineNumber Line number in the CSV file
     * @param data The actual data that caused the error
     * @param error Description of the error
     */
    private void logInvalidData(int lineNumber, String data, String error) {
        try {
            if (invalidWriter != null) {
                invalidWriter.write(lineNumber + ",\"" + data.replace("\"", "\"\"") + "\",\"" + error + "\"\n");
                invalidWriter.flush();
            }
        } catch (IOException e) {
            System.err.println("⚠️  Warning: Could not log invalid data: " + e.getMessage());
        }
    }
    
    /**
     * Filter products by price threshold
     * @param products List of all products
     * @return List of products with price > threshold
     */
    private List<Product> filterExpensiveProducts(List<Product> products) {
        List<Product> expensiveProducts = new ArrayList<>();
        
        for (Product product : products) {
            if (product.isPriceGreaterThan(PRICE_THRESHOLD)) {
                expensiveProducts.add(product);
            }
        }
        
        return expensiveProducts;
    }
    
    /**
     * Write filtered products to a new CSV file
     * @param products List of products to write
     */
    private void writeFilteredProductsToCSV(List<Product> products) {
        try {
            writer = new FileWriter(OUTPUT_FILE);
            
            // Write CSV header
            writer.write("Name,Price\n");
            
            // Write each product
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "\n");
            }
            
            System.out.println("📝 Wrote " + products.size() + " products to " + OUTPUT_FILE);
            
        } catch (IOException e) {
            System.err.println("❌ Error writing output file: " + e.getMessage());
        }
    }
    
    /**
     * Display products in a formatted table
     * @param products List of products to display
     */
    private void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products to display.");
            return;
        }
        
        System.out.println("Name                 | Price");
        System.out.println("-".repeat(40));
        
        for (Product product : products) {
            System.out.println(product);
        }
    }
    
    /**
     * Verify the output file was created correctly
     */
    private void verifyOutputFile() {
        try {
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
            
            // Also show invalid data log
            File invalidFile = new File(INVALID_DATA_FILE);
            if (invalidFile.exists()) {
                System.out.println("\n--- Invalid Data Log ---");
                try (BufferedReader reader = new BufferedReader(new FileReader(INVALID_DATA_FILE))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("❌ Error verifying output file: " + e.getMessage());
        }
    }
    
    /**
     * Close all resources safely in finally block
     */
    private void closeResources() {
        System.out.println("\n🔒 Closing resources...");
        
        // Close reader
        if (reader != null) {
            try {
                reader.close();
                System.out.println("✅ Input file reader closed");
            } catch (IOException e) {
                System.err.println("⚠️  Warning: Error closing reader: " + e.getMessage());
            }
        }
        
        // Close writer
        if (writer != null) {
            try {
                writer.close();
                System.out.println("✅ Output file writer closed");
            } catch (IOException e) {
                System.err.println("⚠️  Warning: Error closing writer: " + e.getMessage());
            }
        }
        
        // Close invalid data writer
        if (invalidWriter != null) {
            try {
                invalidWriter.close();
                System.out.println("✅ Invalid data log writer closed");
            } catch (IOException e) {
                System.err.println("⚠️  Warning: Error closing invalid data writer: " + e.getMessage());
            }
        }
        
        System.out.println("🔒 All resources closed successfully");
    }
}
