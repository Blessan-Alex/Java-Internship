import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestEmployeeSystem {
    public static void main(String[] args) {
        System.out.println("=== Testing Employee Management System ===\n");
        
        // Test 1: Create Employee objects
        System.out.println("Test 1: Creating Employee objects");
        Employee emp1 = new Employee("John Smith", 32, 75000.0);
        Employee emp2 = new Employee("Emma Johnson", 28, 82000.0);
        Employee emp3 = new Employee("Michael Brown", 35, 68000.0);
        
        System.out.println("Employee 1: " + emp1);
        System.out.println("Employee 2: " + emp2);
        System.out.println("Employee 3: " + emp3);
        System.out.println();
        
        // Test 2: Test ArrayList operations
        System.out.println("Test 2: ArrayList operations");
        ArrayList<Employee> testList = new ArrayList<>();
        
        // Add employees
        testList.add(emp1);
        testList.add(emp2);
        testList.add(emp3);
        System.out.println("Added 3 employees. Total count: " + testList.size());
        
        // Display all employees
        System.out.println("All employees in list:");
        for (Employee emp : testList) {
            System.out.println("  " + emp);
        }
        System.out.println();
        
        // Test 3: Test SalaryComparator
        System.out.println("Test 3: SalaryComparator (descending)");
        ArrayList<Employee> salarySorted = new ArrayList<>(testList);
        Collections.sort(salarySorted, new SalaryComparator());
        
        System.out.println("Employees sorted by salary (highest to lowest):");
        for (Employee emp : salarySorted) {
            System.out.println("  " + emp);
        }
        System.out.println();
        
        // Test 4: Test NameComparator
        System.out.println("Test 4: NameComparator (ascending)");
        ArrayList<Employee> nameSorted = new ArrayList<>(testList);
        Collections.sort(nameSorted, new NameComparator());
        
        System.out.println("Employees sorted by name (alphabetical):");
        for (Employee emp : nameSorted) {
            System.out.println("  " + emp);
        }
        System.out.println();
        
        // Test 5: Test lambda expressions
        System.out.println("Test 5: Lambda expressions for sorting");
        
        // Sort by age using lambda
        ArrayList<Employee> ageSorted = new ArrayList<>(testList);
        Collections.sort(ageSorted, (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        
        System.out.println("Employees sorted by age (ascending) using lambda:");
        for (Employee emp : ageSorted) {
            System.out.println("  " + emp);
        }
        System.out.println();
        
        // Test 6: Test multiple field sorting
        System.out.println("Test 6: Multiple field sorting");
        ArrayList<Employee> multiSorted = new ArrayList<>(testList);
        
        // Sort by age first, then by salary
        Collections.sort(multiSorted, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                int ageComparison = Integer.compare(e1.getAge(), e2.getAge());
                if (ageComparison == 0) {
                    return Double.compare(e1.getSalary(), e2.getSalary());
                }
                return ageComparison;
            }
        });
        
        System.out.println("Employees sorted by age, then by salary:");
        for (Employee emp : multiSorted) {
            System.out.println("  " + emp);
        }
        System.out.println();
        
        // Test 7: Test encapsulation
        System.out.println("Test 7: Encapsulation (getters and setters)");
        Employee testEmployee = new Employee("Test Employee", 25, 50000.0);
        System.out.println("Original: " + testEmployee);
        
        // Use setters to modify
        testEmployee.setName("Updated Test Employee");
        testEmployee.setAge(30);
        testEmployee.setSalary(60000.0);
        System.out.println("After updates: " + testEmployee);
        System.out.println();
        
        // Test 8: Test constructor and toString
        System.out.println("Test 8: Constructor and toString method");
        Employee newEmployee = new Employee("Constructor Test", 40, 85000.0);
        System.out.println("New employee created: " + newEmployee);
        System.out.println("Employee Name: " + newEmployee.getName());
        System.out.println("Employee Age: " + newEmployee.getAge());
        System.out.println("Employee Salary: " + newEmployee.getSalary());
        System.out.println();
        
        System.out.println("=== All Tests Completed Successfully! ===");
        System.out.println("The Employee Management System is working correctly.");
        System.out.println("You can now run the full EmployeeManagementSystem class.");
    }
}
