public class Product {
    private String name;
    private double price;
    
    /**
     * Constructor for Product class with validation
     * @param name Product name
     * @param price Product price
     * @throws InvalidProductDataException if data is invalid
     */
    public Product(String name, double price) throws InvalidProductDataException {
        // Validate input data
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductDataException("Product name cannot be null or empty");
        }
        
        if (price < 0) {
            throw new InvalidProductDataException("Product price cannot be negative: " + price);
        }
        
        if (price > 1000000) { // Reasonable upper limit
            throw new InvalidProductDataException("Product price seems unreasonably high: " + price);
        }
        
        this.name = name.trim();
        this.price = price;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) throws InvalidProductDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductDataException("Product name cannot be null or empty");
        }
        this.name = name.trim();
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) throws InvalidProductDataException {
        if (price < 0) {
            throw new InvalidProductDataException("Product price cannot be negative: " + price);
        }
        if (price > 1000000) {
            throw new InvalidProductDataException("Product price seems unreasonably high: " + price);
        }
        this.price = price;
    }
    
    /**
     * toString method for displaying product information
     * @return Formatted string representation of product
     */
    @Override
    public String toString() {
        return String.format("Name: %-20s | Price: $%,8.2f", name, price);
    }
    
    /**
     * Check if product price is greater than specified amount
     * @param threshold The price threshold to check against
     * @return true if price > threshold, false otherwise
     */
    public boolean isPriceGreaterThan(double threshold) {
        return this.price > threshold;
    }
    
    /**
     * Validate product data
     * @return true if product data is valid
     * @throws InvalidProductDataException if validation fails
     */
    public boolean validate() throws InvalidProductDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductDataException("Product name is invalid");
        }
        
        if (price < 0) {
            throw new InvalidProductDataException("Product price is invalid: " + price);
        }
        
        return true;
    }
}
