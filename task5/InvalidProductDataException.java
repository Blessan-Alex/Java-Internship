/**
 * Custom exception for invalid product data
 * Extends Exception to make it a checked exception
 */
public class InvalidProductDataException extends Exception {
    
    /**
     * Default constructor
     */
    public InvalidProductDataException() {
        super("Invalid product data");
    }
    
    /**
     * Constructor with message
     * @param message Error message
     */
    public InvalidProductDataException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and cause
     * @param message Error message
     * @param cause The cause of the exception
     */
    public InvalidProductDataException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor with cause
     * @param cause The cause of the exception
     */
    public InvalidProductDataException(Throwable cause) {
        super(cause);
    }
}
