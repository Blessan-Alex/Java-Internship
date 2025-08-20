import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVProcessor {
    private static final String INPUT_FILE = "products.csv";
    private static final String OUTPUT_FILE = "expensive_products.csv";
    private static final double PRICE_THRESHOLD = 1000.0;
    
    /**
     * Main method to run the CSV processing application
     */
    public static void main(String[] args) {
        CSVProcessor processor = new CSVProcessor();
        
        System.out.println("=== CSV File I/O Processing ===");
        System.out.println("Reading from: " + INPUT_FILE);
        System.out.println("Writing to: " + OUTPUT_FILE);
        System.out.println("Price threshold: $" + PRICE_THRESHOLD);
        System.out.println();
        
        try {
            // Step 1: Create sample products.csv file
            processor.createSampleCSV();
            System.out.println("✅ Sample CSV file created successfully!");
            
            // Step 2: Read and process the CSV file
            List<Product> allProducts = processor.readCSVFile();
            System.out.println("✅ CSV file read successfully!");
            System.out.println("Total products found: " + allProducts.size());
            
            // Display all products
            System.out.println("\n--- All Products ---");
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
            
        } catch (IOException e) {
            System.err.println("❌ Error during file processing: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Create a sample products.csv file with product data
     */
    private void createSampleCSV() throws IOException {
        try (FileWriter writer = new FileWriter(INPUT_FILE)) {
            // Write CSV header
            writer.write("Name,Price\n");
            
            // Write sample product data
            String[] productData = {
                "Laptop", "1299.99",
                "Smartphone", "899.50",
                "Headphones", "299.99",
                "Mouse", "49.99",
                "Keyboard", "129.99",
                "Monitor", "349.99",
                "Tablet", "599.99",
                "Gaming Console", "499.99",
                "Camera", "799.99",
                "Speaker", "199.99",
                "Microphone", "89.99",
                "Webcam", "159.99",
                "Printer", "249.99",
                "Scanner", "179.99",
                "External Hard Drive", "89.99"
            };
            
            for (int i = 0; i < productData.length; i += 2) {
                writer.write(productData[i] + "," + productData[i + 1] + "\n");
            }
        }
        
        System.out.println("📁 Sample CSV file created with " + (15) + " products");
    }
    
    /**
     * Read products from CSV file using BufferedReader
     * @return List of Product objects
     */
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
    
    /**
     * Parse a single line from CSV to create a Product object
     * @param line CSV line to parse
     * @return Product object or null if parsing fails
     */
    private Product parseProductLine(String line) {
        try {
            // Split line by comma
            String[] parts = line.split(",");
            
            // Check if we have both name and price
            if (parts.length >= 2) {
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                
                // Validate data
                if (!name.isEmpty() && price >= 0) {
                    return new Product(name, price);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("⚠️  Warning: Could not parse price in line: " + line);
        }
        
        return null;
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
     * Write filtered products to a new CSV file using FileWriter
     * @param products List of products to write
     */
    private void writeFilteredProductsToCSV(List<Product> products) throws IOException {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            // Write CSV header
            writer.write("Name,Price\n");
            
            // Write each product
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "\n");
            }
        }
        
        System.out.println("📝 Wrote " + products.size() + " products to " + OUTPUT_FILE);
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
}
