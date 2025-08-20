# Interview Questions & Answers - Java OOP & Student Management System

## 1. What is a class in Java?
A class in Java is a blueprint or template that defines the structure and behavior of objects. It serves as a logical container that encapsulates data (attributes/fields) and methods (functions) that operate on that data.

**Key characteristics:**
- **Blueprint**: Defines what an object will look like and how it will behave
- **Encapsulation**: Bundles data and methods together
- **Reusability**: Can create multiple objects from the same class
- **Inheritance**: Can be extended by other classes

**Example from our Student class:**
```java
public class Student {
    // Data fields (attributes)
    private int id;
    private String name;
    private double grade;
    
    // Methods (behavior)
    public Student(int id, String name, double grade) { ... }
    public int getId() { ... }
    public void setName(String name) { ... }
}
```

## 2. What is the difference between a class and an object?
A class and an object are fundamental concepts in OOP, but they serve different purposes:

**Class:**
- **Template/Blueprint**: Defines the structure
- **Static**: Exists in code, doesn't take memory until instantiated
- **One per program**: Multiple objects can be created from one class
- **Abstract**: Represents a concept, not a real entity

**Object:**
- **Instance**: Concrete realization of a class
- **Dynamic**: Takes memory space when created
- **Multiple**: Can create many objects from one class
- **Concrete**: Represents a real entity with actual data

**Example:**
```java
// Class definition (template)
public class Student { ... }

// Objects (instances)
Student john = new Student(1001, "John Smith", 85.5);
Student emma = new Student(1002, "Emma Johnson", 92.0);
// Both objects are instances of the Student class
```

## 3. How does the ArrayList differ from an array?
ArrayList and arrays are both used to store collections of data, but they have significant differences:

**Arrays:**
- **Fixed size**: Cannot be resized after creation
- **Primitive support**: Can store primitive types directly
- **Performance**: Faster access and iteration
- **Memory**: Contiguous memory allocation
- **Syntax**: `int[] numbers = new int[5];`

**ArrayList:**
- **Dynamic size**: Automatically grows and shrinks
- **Object only**: Can only store objects (autoboxing for primitives)
- **Flexibility**: Easy to add/remove elements
- **Memory**: Dynamic allocation with overhead
- **Syntax**: `ArrayList<Integer> numbers = new ArrayList<>();`

**Example comparison:**
```java
// Array - fixed size
int[] array = new int[3];
array[0] = 10;
// array[3] = 20; // ArrayIndexOutOfBoundsException!

// ArrayList - dynamic size
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);
list.add(40); // Automatically grows
```

## 4. What is encapsulation in Java?
Encapsulation is one of the four fundamental principles of OOP that bundles data and methods that operate on that data within a single unit (class) while hiding internal implementation details.

**Key aspects:**
- **Data hiding**: Private fields prevent direct external access
- **Controlled access**: Public methods provide controlled access to data
- **Bundling**: Related data and methods are grouped together
- **Maintenance**: Internal changes don't affect external code

**Example from our Student class:**
```java
public class Student {
    // Private fields - data is hidden
    private int id;
    private String name;
    private double grade;
    
    // Public methods - controlled access
    public int getId() { return id; }
    public void setId(int id) { 
        if (id > 0) { // Validation logic
            this.id = id; 
        }
    }
}
```

**Benefits:**
- **Security**: Prevents unauthorized access to data
- **Flexibility**: Can change internal implementation without affecting external code
- **Maintainability**: Easier to modify and debug
- **Reusability**: Code can be reused in different contexts

## 5. How do you create and use constructors?
A constructor is a special method that initializes an object when it's created. It has the same name as the class and no return type.

**Constructor characteristics:**
- **Same name**: Must match the class name exactly
- **No return type**: Not even `void`
- **Automatic call**: Invoked when `new` keyword is used
- **Overloading**: Can have multiple constructors with different parameters

**Types of constructors:**

**Default constructor (no-args):**
```java
public class Student {
    public Student() {
        // Default values
        this.id = 0;
        this.name = "Unknown";
        this.grade = 0.0;
    }
}
```

**Parameterized constructor:**
```java
public class Student {
    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}
```

**Constructor chaining:**
```java
public class Student {
    public Student() {
        this(0, "Unknown", 0.0); // Calls parameterized constructor
    }
    
    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}
```

## 6. What is method overloading?
Method overloading allows you to define multiple methods with the same name but different parameters within the same class.

**Key characteristics:**
- **Same name**: Methods must have identical names
- **Different parameters**: Must differ in number, type, or order
- **Same class**: All overloaded methods must be in the same class
- **Return type**: Can be different but doesn't count for overloading

**Example:**
```java
public class Student {
    // Constructor overloading
    public Student() { ... }
    public Student(int id) { ... }
    public Student(int id, String name) { ... }
    public Student(int id, String name, double grade) { ... }
    
    // Method overloading
    public void updateGrade(double grade) { ... }
    public void updateGrade(int grade) { ... }
    public void updateGrade(String grade) { ... }
}
```

**Benefits:**
- **Readability**: Clear method names for different operations
- **Flexibility**: Handle different data types and parameter combinations
- **Maintainability**: Related functionality grouped under same name

## 7. How does the remove() method work in ArrayList?
The `remove()` method in ArrayList removes elements from the list. There are several overloaded versions:

**remove(int index):**
```java
ArrayList<Student> students = new ArrayList<>();
students.add(new Student(1001, "John", 85.5));
students.add(new Student(1002, "Emma", 92.0));

students.remove(0); // Removes element at index 0 (John)
// Emma now moves to index 0
```

**remove(Object o):**
```java
Student john = new Student(1001, "John", 85.5);
students.add(john);
students.remove(john); // Removes the specific object
```

**removeIf(Predicate):**
```java
// Remove all students with grades below 80
students.removeIf(student -> student.getGrade() < 80.0);
```

**Important considerations:**
- **Index shifting**: When removing by index, subsequent elements shift left
- **Object equality**: Uses `equals()` method for object removal
- **Return value**: Returns `true` if element was found and removed
- **Performance**: O(n) time complexity for object removal

## 8. What is the purpose of the toString() method?
The `toString()` method provides a string representation of an object, making it easier to display object information in a readable format.

**Default behavior:**
```java
Student student = new Student(1001, "John Smith", 85.5);
System.out.println(student); // Prints: Student@7852e922 (hashcode)
```

**Custom toString() implementation:**
```java
@Override
public String toString() {
    return String.format("ID: %d | Name: %-20s | Grade: %.2f", id, name, grade);
}

// Now prints: ID: 1001 | Name: John Smith           | Grade: 85.50
```

**Benefits:**
- **Debugging**: Easy to see object state during development
- **Logging**: Useful for logging object information
- **Display**: User-friendly output in console applications
- **Testing**: Easier to verify object state in tests

**Best practices:**
- Always override `toString()` for meaningful output
- Include all relevant object state
- Use consistent formatting
- Consider performance for large objects

## 9. Why use ArrayList instead of arrays?
ArrayList provides several advantages over traditional arrays:

**Dynamic sizing:**
```java
// Array - fixed size
int[] array = new int[5];
// Cannot add more elements without creating new array

// ArrayList - grows automatically
ArrayList<Integer> list = new ArrayList<>();
list.add(1); // Size: 1
list.add(2); // Size: 2
list.add(3); // Size: 3
// Continues growing as needed
```

**Built-in methods:**
```java
ArrayList<Student> students = new ArrayList<>();

// Easy operations
students.add(newStudent);           // Add element
students.remove(student);           // Remove element
students.contains(student);         // Check existence
students.size();                    // Get size
students.clear();                   // Remove all elements
students.isEmpty();                 // Check if empty
```

**Type safety:**
```java
// Array - can mix types (if declared as Object[])
Object[] mixedArray = {1, "hello", 3.14};

// ArrayList - type-safe
ArrayList<String> stringList = new ArrayList<>();
stringList.add("hello");
// stringList.add(123); // Compilation error!
```

**Memory management:**
- Automatic memory allocation and deallocation
- No need to track array size manually
- Efficient memory usage

## 10. What is the difference between ArrayList and LinkedList?
Both implement the List interface but have different internal structures and performance characteristics:

**ArrayList:**
- **Internal structure**: Dynamic array
- **Memory**: Contiguous memory allocation
- **Access**: O(1) for random access
- **Insertion/Deletion**: O(n) - elements must shift
- **Memory overhead**: Lower
- **Best for**: Frequent access, infrequent modifications

**LinkedList:**
- **Internal structure**: Doubly-linked list
- **Memory**: Non-contiguous, linked nodes
- **Access**: O(n) for random access
- **Insertion/Deletion**: O(1) for beginning/end, O(n) for middle
- **Memory overhead**: Higher (node references)
- **Best for**: Frequent insertions/deletions, infrequent access

**Performance comparison:**
```java
ArrayList<Integer> arrayList = new ArrayList<>();
LinkedList<Integer> linkedList = new LinkedList<>();

// Adding elements
for (int i = 0; i < 100000; i++) {
    arrayList.add(i);      // O(1) amortized
    linkedList.add(i);     // O(1)
}

// Random access
arrayList.get(50000);      // O(1)
linkedList.get(50000);     // O(n)

// Insertion at beginning
arrayList.add(0, 999);     // O(n) - shifts all elements
linkedList.add(0, 999);    // O(1) - just update references
```

**When to use which:**
- **ArrayList**: When you need fast random access and infrequent modifications
- **LinkedList**: When you need fast insertions/deletions and infrequent random access
- **General rule**: Use ArrayList unless you specifically need LinkedList's benefits
