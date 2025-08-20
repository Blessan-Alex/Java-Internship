# Interview Questions & Answers - Java Collections & Sorting

## 1. What is a Comparator in Java?
A Comparator in Java is a functional interface that defines a comparison function for ordering objects. It's used to sort collections of objects when the objects don't implement the `Comparable` interface, or when you want to sort them differently than their natural ordering.

**Key characteristics:**
- **Functional Interface**: Has a single abstract method `compare(T o1, T o2)`
- **External Sorting**: Provides sorting logic separate from the object class
- **Flexible**: Can create multiple different sorting orders for the same objects
- **Collections Integration**: Works seamlessly with `Collections.sort()` and other sorting methods

**Example from our Employee system:**
```java
public class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        // Sort by salary in descending order (highest first)
        return Double.compare(emp2.getSalary(), emp1.getSalary());
    }
}

// Usage
Collections.sort(employees, new SalaryComparator());
```

**The compare() method contract:**
- Returns negative if `o1 < o2`
- Returns positive if `o1 > o2`
- Returns zero if `o1 == o2`

## 2. Difference between Comparable and Comparator?

**Comparable:**
- **Internal Sorting**: The object itself defines how it should be ordered
- **Natural Ordering**: Defines the default, natural way to sort objects
- **Single Implementation**: Can only implement one sorting order per class
- **Modifies Class**: Requires modifying the original class
- **Method**: `compareTo(T o)` method

**Comparator:**
- **External Sorting**: Separate class defines sorting logic
- **Custom Ordering**: Can create multiple different sorting orders
- **Multiple Implementations**: Can create many different comparators
- **No Class Modification**: Doesn't require changing the original class
- **Method**: `compare(T o1, T o2)` method

**Example comparison:**
```java
// Comparable - Employee implements Comparable<Employee>
public class Employee implements Comparable<Employee> {
    @Override
    public int compareTo(Employee other) {
        return this.name.compareTo(other.name); // Natural ordering by name
    }
}

// Comparator - Separate class
public class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return Double.compare(emp2.getSalary(), emp1.getSalary()); // Custom ordering
    }
}

// Usage
Collections.sort(employees);                    // Uses Comparable (natural order)
Collections.sort(employees, new SalaryComparator()); // Uses Comparator (custom order)
```

## 3. How does Collections.sort() work?

`Collections.sort()` is a utility method that sorts a List using a specific sorting algorithm. It has two main overloads:

**Collections.sort(List<T> list):**
- Uses the natural ordering defined by the `Comparable` interface
- Requires all elements to implement `Comparable<T>`
- Throws `ClassCastException` if elements don't implement `Comparable`

**Collections.sort(List<T> list, Comparator<? super T> c):**
- Uses the provided `Comparator` for ordering
- More flexible as it doesn't require elements to implement `Comparable`
- Allows custom sorting logic

**Internal working:**
```java
// The sort method uses a modified merge sort algorithm
public static <T> void sort(List<T> list, Comparator<? super T> c) {
    list.sort(c); // Delegates to List.sort()
}

// List.sort() uses TimSort (hybrid of merge sort and insertion sort)
default void sort(Comparator<? super T> c) {
    Object[] a = this.toArray();
    Arrays.sort(a, (Comparator) c);
    ListIterator<T> i = this.listIterator();
    for (int j = 0; j < a.length; j++) {
        i.next();
        i.set((T) a[j]);
    }
}
```

**Example from our system:**
```java
// Sort by salary using SalaryComparator
Collections.sort(employees, new SalaryComparator());

// Sort by name using NameComparator
Collections.sort(employees, new NameComparator());

// Sort by natural order (if Employee implemented Comparable)
// Collections.sort(employees);
```

## 4. How do you sort objects in descending order?

There are several ways to sort objects in descending order in Java:

**Method 1: Reverse the comparison in Comparator**
```java
public class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        // For descending order, reverse the comparison
        return Double.compare(emp2.getSalary(), emp1.getSalary());
        // Instead of: return Double.compare(emp1.getSalary(), emp2.getSalary());
    }
}
```

**Method 2: Use Collections.reverseOrder()**
```java
// For objects that implement Comparable
Collections.sort(employees, Collections.reverseOrder());

// For custom Comparator
Collections.sort(employees, Collections.reverseOrder(new SalaryComparator()));
```

**Method 3: Use Comparator.reversed()**
```java
// Create ascending comparator first
Comparator<Employee> ascendingComparator = Comparator.comparing(Employee::getSalary);
// Then reverse it
Collections.sort(employees, ascendingComparator.reversed());
```

**Method 4: Use lambda with reversed comparison**
```java
// Sort by salary in descending order
Collections.sort(employees, (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));

// Sort by age in descending order
Collections.sort(employees, (e1, e2) -> Integer.compare(e2.getAge(), e1.getAge()));
```

**Example from our system:**
```java
// SalaryComparator sorts by salary in descending order
public class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        // emp2 first, then emp1 for descending order
        return Double.compare(emp2.getSalary(), emp1.getSalary());
    }
}
```

## 5. What is the difference between ArrayList and LinkedList?

Both implement the List interface but have different internal structures and performance characteristics:

**ArrayList:**
- **Internal structure**: Dynamic array
- **Memory**: Contiguous memory allocation
- **Access**: O(1) for random access
- **Insertion/Deletion**: O(n) - elements must shift
- **Memory overhead**: Lower
- **Best for**: Frequent access, infrequent modifications
- **Implementation**: Uses resizable array internally

**LinkedList:**
- **Internal structure**: Doubly-linked list
- **Memory**: Non-contiguous, linked nodes
- **Access**: O(n) for random access
- **Insertion/Deletion**: O(1) for beginning/end, O(n) for middle
- **Memory overhead**: Higher (node references)
- **Best for**: Frequent insertions/deletions, infrequent access
- **Implementation**: Uses Node objects with next/previous references

**Performance comparison:**
```java
ArrayList<Employee> arrayList = new ArrayList<>();
LinkedList<Employee> linkedList = new LinkedList<>();

// Adding elements
for (int i = 0; i < 100000; i++) {
    arrayList.add(new Employee("Emp" + i, 25, 50000.0));      // O(1) amortized
    linkedList.add(new Employee("Emp" + i, 25, 50000.0));     // O(1)
}

// Random access
Employee emp1 = arrayList.get(50000);      // O(1)
Employee emp2 = linkedList.get(50000);     // O(n)

// Insertion at beginning
arrayList.add(0, new Employee("New", 25, 50000.0));     // O(n) - shifts all elements
linkedList.add(0, new Employee("New", 25, 50000.0));    // O(1) - just update references
```

**When to use which:**
- **ArrayList**: When you need fast random access and infrequent modifications
- **LinkedList**: When you need fast insertions/deletions and infrequent random access
- **General rule**: Use ArrayList unless you specifically need LinkedList's benefits

## 6. How does compare() method work in Comparator?

The `compare()` method is the core of the Comparator interface and defines how two objects should be ordered relative to each other.

**Method signature:**
```java
int compare(T o1, T o2)
```

**Return value contract:**
- **Negative value (-1)**: `o1` should come before `o2` in sorted order
- **Positive value (+1)**: `o1` should come after `o2` in sorted order
- **Zero (0)**: `o1` and `o2` are considered equal for sorting purposes

**Implementation examples:**
```java
// Ascending order by salary
public class SalaryAscendingComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return Double.compare(emp1.getSalary(), emp2.getSalary());
        // If emp1.salary < emp2.salary, returns negative (emp1 comes first)
        // If emp1.salary > emp2.salary, returns positive (emp1 comes after)
        // If emp1.salary == emp2.salary, returns zero (equal)
    }
}

// Descending order by salary
public class SalaryDescendingComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return Double.compare(emp2.getSalary(), emp1.getSalary());
        // Reversed comparison for descending order
    }
}

// Case-insensitive name comparison
public class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.getName().compareToIgnoreCase(emp2.getName());
    }
}
```

**Important considerations:**
- **Consistency**: Must be consistent with equals() method
- **Transitivity**: If A < B and B < C, then A < C
- **Symmetry**: If A < B, then B > A
- **Null handling**: Consider how to handle null values

## 7. Can we sort a list without modifying it?

Yes, there are several ways to sort a list without modifying the original list:

**Method 1: Create a copy and sort the copy**
```java
// Create a copy of the original list
ArrayList<Employee> sortedList = new ArrayList<>(employees);
Collections.sort(sortedList, new SalaryComparator());

// Original list remains unchanged
System.out.println("Original: " + employees);
System.out.println("Sorted: " + sortedList);
```

**Method 2: Use Stream API (Java 8+)**
```java
// Create a new sorted list without modifying original
List<Employee> sortedList = employees.stream()
    .sorted(new SalaryComparator())
    .toList();

// Original list is unchanged
System.out.println("Original size: " + employees.size());
System.out.println("Sorted list size: " + sortedList.size());
```

**Method 3: Use List.copyOf() (Java 10+)**
```java
// Create immutable copy
List<Employee> immutableCopy = List.copyOf(employees);
// Note: This creates an immutable list, so it can't be sorted in place
```

**Example from our system:**
```java
private void sortWithoutModifyingOriginal() {
    System.out.println("Original list (unchanged):");
    displayEmployees();
    
    // Create a new sorted list without modifying the original
    List<Employee> sortedList = employees.stream()
        .sorted(new SalaryComparator())
        .toList();
    
    System.out.println("\nNew sorted list (original unchanged):");
    // Display sorted list...
    
    // Verify original list is unchanged
    System.out.println("\nVerification - Original list is still unchanged:");
    displayEmployees();
}
```

**Benefits of non-modifying sorting:**
- **Data integrity**: Original data remains unchanged
- **Multiple views**: Can have different sorted views of the same data
- **Thread safety**: Original list can be safely shared between threads
- **Debugging**: Easier to debug when original data is preserved

## 8. What is lambda expression in Java sorting?

Lambda expressions in Java provide a concise way to implement functional interfaces, including Comparators. They were introduced in Java 8 and make sorting code much more readable and concise.

**Traditional approach vs Lambda:**
```java
// Traditional anonymous class approach
Collections.sort(employees, new Comparator<Employee>() {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e1.getSalary(), e2.getSalary());
    }
});

// Lambda expression approach
Collections.sort(employees, (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
```

**Lambda syntax for sorting:**
```java
// Basic lambda syntax: (parameters) -> expression
Collections.sort(employees, (e1, e2) -> e1.getName().compareTo(e2.getName()));

// Multiple statements in lambda
Collections.sort(employees, (e1, e2) -> {
    int nameComparison = e1.getName().compareTo(e2.getName());
    if (nameComparison != 0) {
        return nameComparison;
    }
    return Integer.compare(e1.getAge(), e2.getAge());
});
```

**Common lambda sorting patterns:**
```java
// Sort by age (ascending)
Collections.sort(employees, (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));

// Sort by salary (descending)
Collections.sort(employees, (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));

// Sort by name length
Collections.sort(employees, (e1, e2) -> Integer.compare(e1.getName().length(), e2.getName().length()));

// Sort by multiple fields
Collections.sort(employees, (e1, e2) -> {
    int ageComparison = Integer.compare(e1.getAge(), e2.getAge());
    return ageComparison != 0 ? ageComparison : 
           Double.compare(e1.getSalary(), e2.getSalary());
});
```

**Example from our system:**
```java
private void demonstrateLambdaSorting() {
    System.out.println("1. Sorting by age (ascending) using lambda:");
    ArrayList<Employee> sortedByAge = new ArrayList<>(employees);
    Collections.sort(sortedByAge, (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
    
    System.out.println("2. Sorting by salary (ascending) using lambda:");
    ArrayList<Employee> sortedBySalaryAsc = new ArrayList<>(employees);
    Collections.sort(sortedBySalaryAsc, (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
    
    System.out.println("3. Sorting by name length using lambda:");
    ArrayList<Employee> sortedByNameLength = new ArrayList<>(employees);
    Collections.sort(sortedByNameLength, (e1, e2) -> Integer.compare(e1.getName().length(), e2.getName().length()));
}
```

## 9. How do you sort by multiple fields?

Sorting by multiple fields involves creating a Comparator that compares objects by one field first, and if they're equal, then by another field.

**Method 1: Custom Comparator with multiple field comparison**
```java
public class MultiFieldComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        // First compare by age
        int ageComparison = Integer.compare(e1.getAge(), e2.getAge());
        
        // If ages are equal, compare by salary
        if (ageComparison != 0) {
            return ageComparison;
        }
        
        // If ages and salaries are equal, compare by name
        return e1.getName().compareTo(e2.getName());
    }
}
```

**Method 2: Using anonymous Comparator**
```java
Collections.sort(employees, new Comparator<Employee>() {
    @Override
    public int compare(Employee e1, Employee e2) {
        // Sort by age first (ascending)
        int ageComparison = Integer.compare(e1.getAge(), e2.getAge());
        
        // If ages are equal, sort by salary (descending)
        if (ageComparison == 0) {
            return Double.compare(e2.getSalary(), e1.getSalary());
        }
        
        return ageComparison;
    }
});
```

**Method 3: Using lambda expressions**
```java
Collections.sort(employees, (e1, e2) -> {
    // Sort by age first
    int ageComparison = Integer.compare(e1.getAge(), e2.getAge());
    
    // If ages are equal, sort by salary
    if (ageComparison != 0) {
        return ageComparison;
    }
    
    // If ages are equal, sort by name
    return e1.getName().compareTo(e2.getName());
});
```

**Method 4: Using Comparator.comparing() (Java 8+)**
```java
// Sort by age, then by salary, then by name
Collections.sort(employees, 
    Comparator.comparing(Employee::getAge)
              .thenComparing(Employee::getSalary)
              .thenComparing(Employee::getName));

// Sort by age ascending, then by salary descending
Collections.sort(employees, 
    Comparator.comparing(Employee::getAge)
              .thenComparing(Comparator.comparing(Employee::getSalary).reversed()));
```

**Example from our system:**
```java
private void sortByMultipleFields() {
    ArrayList<Employee> sortedByMultiple = new ArrayList<>(employees);
    
    // Sort by age first, then by salary (both ascending)
    Collections.sort(sortedByMultiple, new Comparator<Employee>() {
        @Override
        public int compare(Employee e1, Employee e2) {
            // First compare by age
            int ageComparison = Integer.compare(e1.getAge(), e2.getAge());
            
            // If ages are equal, compare by salary
            if (ageComparison == 0) {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
            
            return ageComparison;
        }
    });
    
    // Display results with grouping
    System.out.println("Employees sorted by age (ascending), then by salary (ascending):");
    // ... display logic
    
    // Show grouping by age
    System.out.println("\nGrouped by age:");
    int currentAge = -1;
    for (Employee emp : sortedByMultiple) {
        if (emp.getAge() != currentAge) {
            currentAge = emp.getAge();
            System.out.println("\nAge " + currentAge + ":");
        }
        System.out.println("  " + emp);
    }
}
```

## 10. Why is List preferred for dynamic data?

List is preferred for dynamic data because it provides several advantages over arrays and other collection types:

**Dynamic sizing:**
```java
// Array - fixed size
Employee[] employeeArray = new Employee[5];
// Cannot add more than 5 employees without creating new array

// List - grows automatically
List<Employee> employeeList = new ArrayList<>();
employeeList.add(new Employee("John", 25, 50000.0)); // Size: 1
employeeList.add(new Employee("Emma", 28, 60000.0)); // Size: 2
// Continues growing as needed
```

**Built-in methods for dynamic operations:**
```java
List<Employee> employees = new ArrayList<>();

// Dynamic operations
employees.add(newEmployee);           // Add element
employees.remove(employee);           // Remove element
employees.add(0, newEmployee);        // Insert at specific position
employees.set(0, updatedEmployee);    // Update element
employees.clear();                    // Remove all elements
employees.isEmpty();                  // Check if empty
employees.size();                     // Get current size
```

**Flexibility in data manipulation:**
```java
// Easy to add/remove elements
employees.add(new Employee("New Hire", 22, 45000.0));
employees.removeIf(emp -> emp.getAge() > 65); // Remove retirees

// Easy to modify existing elements
for (int i = 0; i < employees.size(); i++) {
    Employee emp = employees.get(i);
    if (emp.getSalary() < 50000) {
        employees.set(i, new Employee(emp.getName(), emp.getAge(), emp.getSalary() * 1.1));
    }
}
```

**Integration with sorting and other operations:**
```java
// Easy to sort
Collections.sort(employees, new SalaryComparator());

// Easy to filter
List<Employee> highEarners = employees.stream()
    .filter(emp -> emp.getSalary() > 80000)
    .toList();

// Easy to transform
List<String> names = employees.stream()
    .map(Employee::getName)
    .toList();
```

**Memory management:**
- Automatic memory allocation and deallocation
- No need to track size manually
- Efficient memory usage with growth strategies
- Automatic resizing when capacity is exceeded

**Example from our system:**
```java
private ArrayList<Employee> employees;

public EmployeeManagementSystem() {
    employees = new ArrayList<>();  // Dynamic list
    initializeSampleData();         // Add initial data
}

private void initializeSampleData() {
    // Easy to add multiple employees
    employees.add(new Employee("John Smith", 32, 75000.0));
    employees.add(new Employee("Emma Johnson", 28, 82000.0));
    employees.add(new Employee("Michael Brown", 35, 68000.0));
    // List grows automatically as needed
}

// Easy to sort without worrying about size
public void sortBySalaryDescending() {
    ArrayList<Employee> sortedBySalary = new ArrayList<>(employees); // Create copy
    Collections.sort(sortedBySalary, new SalaryComparator());        // Sort copy
    // Original list remains unchanged
}
```

**When to use List vs other collections:**
- **List**: When you need ordered, indexed access with dynamic sizing
- **Set**: When you need unique elements without ordering
- **Map**: When you need key-value pairs
- **Array**: When you need fixed-size, primitive performance
- **General rule**: Use List unless you specifically need Set or Map functionality
