import java.util.Scanner;

public class EvenOddChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;
        
        System.out.println("=== Even/Odd Number Checker ===");
        System.out.println("This program checks whether a number is even or odd.");
        
        while (continueProgram) {
            try {
                System.out.print("\nEnter a number (or 'exit' to quit): ");
                
                // Check if user wants to exit
                if (scanner.hasNext("exit")) {
                    continueProgram = false;
                    scanner.next(); // consume "exit"
                    continue;
                }
                
                // Check if input is an integer
                if (scanner.hasNextInt()) {
                    int number = scanner.nextInt();
                    checkEvenOdd(number);
                } else {
                    System.out.println("Error: Please enter a valid integer or 'exit' to quit.");
                    scanner.next(); // consume invalid input
                }
                
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // clear the scanner buffer
            }
        }
        
        System.out.println("\nThank you for using the Even/Odd Number Checker!");
        scanner.close();
    }
    
    /**
     * Checks if a number is even or odd using the modulus operator
     * @param number The number to check
     */
    public static void checkEvenOdd(int number) {
        if (number % 2 == 0) {
            System.out.println(number + " is EVEN");
        } else {
            System.out.println(number + " is ODD");
        }
    }
}
