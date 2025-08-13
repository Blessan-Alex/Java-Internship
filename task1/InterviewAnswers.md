# Interview Questions & Answers

## 1. What is the modulus operator in Java?
The modulus operator (`%`) in Java returns the remainder of a division operation. It's used to determine if a number is divisible by another number without a remainder.

**Example:**
```java
int remainder = 17 % 5;  // remainder = 2 (17 ÷ 5 = 3 remainder 2)
boolean isEven = (number % 2 == 0);  // true if number is even
```

## 2. How do you use the if-else statement in Java?
The if-else statement in Java provides conditional execution of code blocks based on boolean expressions.

**Syntax:**
```java
if (condition) {
    // code to execute if condition is true
} else if (anotherCondition) {
    // code to execute if anotherCondition is true
} else {
    // code to execute if all conditions are false
}
```

**Example:**
```java
if (number % 2 == 0) {
    System.out.println("Even");
} else {
    System.out.println("Odd");
}
```

## 3. What is the purpose of the Scanner class in Java?
The Scanner class in Java is used to parse primitive types and strings from input sources (like keyboard input, files, or strings). It provides methods to read different types of data from the user.

**Key methods:**
- `nextInt()` - reads an integer
- `nextDouble()` - reads a double
- `nextLine()` - reads a line of text
- `hasNextInt()` - checks if next input is an integer

## 4. How do you take integer input from the user in Java?
```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
System.out.print("Enter a number: ");
int number = scanner.nextInt();
```

**Best practice with error handling:**
```java
if (scanner.hasNextInt()) {
    int number = scanner.nextInt();
    // process the number
} else {
    System.out.println("Invalid input. Please enter an integer.");
    scanner.next(); // consume invalid input
}
```

## 5. What are the basic data types used in Java for numeric values?
Java has several primitive numeric data types:

**Integer types:**
- `byte` (8-bit): -128 to 127
- `short` (16-bit): -32,768 to 32,767
- `int` (32-bit): -2^31 to 2^31-1
- `long` (64-bit): -2^63 to 2^63-1

**Floating-point types:**
- `float` (32-bit): ~6-7 decimal digits precision
- `double` (64-bit): ~15-16 decimal digits precision

## 6. Can an if condition contain multiple logical expressions?
Yes, if conditions can contain multiple logical expressions using logical operators:

**Logical AND (`&&`):**
```java
if (age >= 18 && hasLicense) {
    System.out.println("Can drive");
}
```

**Logical OR (`||`):**
```java
if (isStudent || isSenior) {
    System.out.println("Eligible for discount");
}
```

**Logical NOT (`!`):**
```java
if (!isValid) {
    System.out.println("Invalid input");
}
```

## 7. What happens if the user enters a non-integer value in a program expecting an integer?
If a user enters a non-integer value when the program expects an integer, it can cause runtime exceptions:

**Without error handling:**
```java
int number = scanner.nextInt(); // Throws InputMismatchException
```

**With proper error handling:**
```java
try {
    if (scanner.hasNextInt()) {
        int number = scanner.nextInt();
    } else {
        System.out.println("Please enter a valid integer");
        scanner.next(); // consume invalid input
    }
} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}
```

## 8. How does Java handle negative numbers with the modulus operator?
Java's modulus operator works with negative numbers by preserving the sign of the dividend (left operand):

**Examples:**
```java
-17 % 5 = -2    // negative result because -17 is negative
17 % -5 = 2     // positive result because 17 is positive
-17 % -5 = -2   // negative result because -17 is negative
```

**For even/odd checking:**
```java
// Both positive and negative numbers work correctly
-4 % 2 = 0   // -4 is even
-3 % 2 = -1  // -3 is odd
```

## 9. What is the default value of an integer variable in Java?
In Java, the default value of an integer variable depends on where it's declared:

**Instance variables (class fields):**
- `int`: 0
- `long`: 0L
- `byte`: 0
- `short`: 0

**Local variables (method variables):**
- No default value - must be initialized before use
- Compiler error if accessed without initialization

**Example:**
```java
public class Example {
    int instanceVar;  // defaults to 0
    
    public void method() {
        int localVar;  // no default value
        // System.out.println(localVar); // Compilation error
        localVar = 5;  // must initialize first
    }
}
```

## 10. How would you extend the Even/Odd checker to repeat until the user exits?
The Even/Odd checker can be extended using a loop structure. Here are several approaches:

**Using while loop with boolean flag:**
```java
boolean continueProgram = true;
while (continueProgram) {
    // get user input
    if (userWantsToExit) {
        continueProgram = false;
    }
}
```

**Using do-while loop:**
```java
do {
    // get user input and process
} while (userWantsToContinue);
```

**Using infinite loop with break:**
```java
while (true) {
    // get user input
    if (userWantsToExit) {
        break;
    }
}
```

**Key considerations:**
- Provide clear exit instructions to the user
- Handle invalid input gracefully
- Clean up resources (close Scanner)
- Use appropriate loop control structures
