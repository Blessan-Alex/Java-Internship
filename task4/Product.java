public class Product {
    private String name;
    private double price;
    
    /**
     * Constructor for Product class
     * @param name Product name
     * @param price Product price
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
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
}
