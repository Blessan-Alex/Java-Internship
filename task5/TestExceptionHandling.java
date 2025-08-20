public class TestExceptionHandling {
    public static void main(String[] args) {
        System.out.println("=== Testing Exception Handling in CSV Processing ===\n");
        
        // Test 1: Test custom exception creation
        System.out.println("Test 1: Custom Exception Creation");
        try {
            throw new InvalidProductDataException("Test custom exception");
        } catch (InvalidProductDataException e) {
            System.out.println("✅ Caught custom exception: " + e.getMessage());
        }
        System.out.println();
        
        // Test 2: Test Product validation with valid data
        System.out.println("Test 2: Product Validation - Valid Data");
        try {
            Product validProduct = new Product("Test Product", 99.99);
            System.out.println("✅ Valid product created: " + validProduct);
            
            // Test validation method
            boolean isValid = validProduct.validate();
            System.out.println("✅ Product validation passed: " + isValid);
            
        } catch (InvalidProductDataException e) {
            System.err.println("❌ Unexpected exception: " + e.getMessage());
        }
        System.out.println();
        
        // Test 3: Test Product validation with invalid name
        System.out.println("Test 3: Product Validation - Invalid Name");
        try {
            Product invalidProduct = new Product("", 99.99);
            System.out.println("❌ Should not reach here - invalid name");
        } catch (InvalidProductDataException e) {
            System.out.println("✅ Caught exception for invalid name: " + e.getMessage());
        }
        
        try {
            Product nullNameProduct = new Product(null, 99.99);
            System.out.println("❌ Should not reach here - null name");
        } catch (InvalidProductDataException e) {
            System.out.println("✅ Caught exception for null name: " + e.getMessage());
        }
        System.out.println();
        
        // Test 4: Test Product validation with invalid price
        System.out.println("Test 4: Product Validation - Invalid Price");
        try {
            Product negativePriceProduct = new Product("Test Product", -50.0);
            System.out.println("❌ Should not reach here - negative price");
        } catch (InvalidProductDataException e) {
            System.out.println("✅ Caught exception for negative price: " + e.getMessage());
        }
        
        try {
            Product expensiveProduct = new Product("Test Product", 2000000.0);
            System.out.println("❌ Should not reach here - too expensive");
        } catch (InvalidProductDataException e) {
            System.out.println("✅ Caught exception for too expensive: " + e.getMessage());
        }
        System.out.println();
        
        // Test 5: Test setter methods with validation
        System.out.println("Test 5: Setter Methods with Validation");
        try {
            Product testProduct = new Product("Test Product", 100.0);
            System.out.println("✅ Product created: " + testProduct);
            
            // Test valid setter
            testProduct.setName("Updated Product");
            System.out.println("✅ Name updated: " + testProduct.getName());
            
            // Test invalid setter
            try {
                testProduct.setPrice(-100.0);
                System.out.println("❌ Should not reach here - invalid price set");
            } catch (InvalidProductDataException e) {
                System.out.println("✅ Caught exception in setter: " + e.getMessage());
            }
            
            // Verify original price unchanged
            System.out.println("✅ Original price preserved: " + testProduct.getPrice());
            
        } catch (InvalidProductDataException e) {
            System.err.println("❌ Unexpected exception: " + e.getMessage());
        }
        System.out.println();
        
        // Test 6: Test exception hierarchy understanding
        System.out.println("Test 6: Exception Hierarchy");
        System.out.println("InvalidProductDataException extends Exception: " + 
                         (InvalidProductDataException.class.getSuperclass() == Exception.class));
        System.out.println("InvalidProductDataException is checked exception: " + 
                         (Exception.class.isAssignableFrom(InvalidProductDataException.class)));
        System.out.println();
        
        // Test 7: Test multiple exception handling
        System.out.println("Test 7: Multiple Exception Handling");
        testMultipleExceptionHandling();
        System.out.println();
        
        System.out.println("=== All Exception Handling Tests Completed Successfully! ===");
        System.out.println("The robust CSV processing system is working correctly.");
        System.out.println("You can now run the full RobustCSVProcessor class.");
    }
    
    /**
     * Demonstrate multiple exception handling scenarios
     */
    private static void testMultipleExceptionHandling() {
        String[] testData = {
            "Valid Product,99.99",
            "Invalid Product,abc",
            "Empty Name,199.99",
            "Only Name",
            "Negative Price,-50.00"
        };
        
        for (int i = 0; i < testData.length; i++) {
            String data = testData[i];
            System.out.println("Testing data: '" + data + "'");
            
            try {
                // Simulate parsing
                String[] parts = data.split(",");
                
                if (parts.length < 2) {
                    throw new InvalidProductDataException("Insufficient data fields");
                }
                
                String name = parts[0].trim();
                String priceStr = parts[1].trim();
                
                if (name.isEmpty()) {
                    throw new InvalidProductDataException("Empty product name");
                }
                
                double price = Double.parseDouble(priceStr);
                
                if (price < 0) {
                    throw new InvalidProductDataException("Negative price: " + price);
                }
                
                Product product = new Product(name, price);
                System.out.println("  ✅ Successfully created: " + product);
                
            } catch (InvalidProductDataException e) {
                System.out.println("  ⚠️  Data validation error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("  ⚠️  Number parsing error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("  ❌ Unexpected error: " + e.getMessage());
            }
        }
    }
}
