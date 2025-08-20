import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeManagementSystem {
    private ArrayList<Employee> employees;
    
    /**
     * Constructor initializes the employee list
     */
    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
        initializeSampleData();
    }
    
    /**
     * Main method to run the Employee Management System
     */
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.run();
    }
    
    /**
     * Main program execution
     */
    public void run() {
        System.out.println("=== Employee Management System - Collections & Sorting ===\n");
        
        // Display original list
        System.out.println("Original Employee List:");
        displayEmployees();
        
        // Sort by salary (descending) using SalaryComparator
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SORTING BY SALARY (DESCENDING) - Using SalaryComparator");
        System.out.println("=".repeat(80));
        sortBySalaryDescending();
        
        // Sort by name (ascending) using NameComparator
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SORTING BY NAME (ASCENDING) - Using NameComparator");
        System.out.println("=".repeat(80));
        sortByNameAscending();
        
        // Demonstrate lambda expressions for sorting
        System.out.println("\n" + "=".repeat(80));
        System.out.println("LAMBDA EXPRESSIONS FOR SORTING");
        System.out.println("=".repeat(80));
        demonstrateLambdaSorting();
        
        // Demonstrate multiple field sorting
        System.out.println("\n" + "=".repeat(80));
        System.out.println("MULTIPLE FIELD SORTING - Age then Salary");
        System.out.println("=".repeat(80));
        sortByMultipleFields();
        
        // Demonstrate sorting without modifying original list
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SORTING WITHOUT MODIFYING ORIGINAL LIST");
        System.out.println("=".repeat(80));
        sortWithoutModifyingOriginal();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ALL SORTING DEMONSTRATIONS COMPLETED!");
        System.out.println("=".repeat(80));
    }
    
    /**
     * Initialize sample employee data
     */
    private void initializeSampleData() {
        employees.add(new Employee("John Smith", 32, 75000.0));
        employees.add(new Employee("Emma Johnson", 28, 82000.0));
        employees.add(new Employee("Michael Brown", 35, 68000.0));
        employees.add(new Employee("Sarah Davis", 29, 95000.0));
        employees.add(new Employee("David Wilson", 31, 72000.0));
        employees.add(new Employee("Lisa Anderson", 27, 88000.0));
        employees.add(new Employee("Robert Taylor", 33, 65000.0));
        employees.add(new Employee("Jennifer Lee", 30, 78000.0));
        employees.add(new Employee("Christopher Garcia", 34, 92000.0));
        employees.add(new Employee("Amanda Martinez", 26, 70000.0));
    }
    
    /**
     * Display all employees in a formatted table
     */
    private void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system.");
            return;
        }
        
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Name                 | Age | Salary");
        System.out.println("-".repeat(50));
        
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    /**
     * Sort employees by salary in descending order using SalaryComparator
     */
    private void sortBySalaryDescending() {
        // Create a copy to avoid modifying the original list
        ArrayList<Employee> sortedBySalary = new ArrayList<>(employees);
        
        // Sort using Collections.sort() with SalaryComparator
        Collections.sort(sortedBySalary, new SalaryComparator());
        
        System.out.println("Employees sorted by salary (highest to lowest):");
        System.out.println("Name                 | Age | Salary");
        System.out.println("-".repeat(50));
        
        for (Employee emp : sortedBySalary) {
            System.out.println(emp);
        }
        
        // Show top 3 highest paid employees
        System.out.println("\nTop 3 Highest Paid Employees:");
        for (int i = 0; i < Math.min(3, sortedBySalary.size()); i++) {
            System.out.println((i + 1) + ". " + sortedBySalary.get(i));
        }
    }
    
    /**
     * Sort employees by name in ascending order using NameComparator
     */
    private void sortByNameAscending() {
        // Create a copy to avoid modifying the original list
        ArrayList<Employee> sortedByName = new ArrayList<>(employees);
        
        // Sort using Collections.sort() with NameComparator
        Collections.sort(sortedByName, new NameComparator());
        
        System.out.println("Employees sorted by name (alphabetical):");
        System.out.println("Name                 | Age | Salary");
        System.out.println("-".repeat(50));
        
        for (Employee emp : sortedByName) {
            System.out.println(emp);
        }
        
        // Show first and last alphabetically
        if (!sortedByName.isEmpty()) {
            System.out.println("\nFirst alphabetically: " + sortedByName.get(0).getName());
            System.out.println("Last alphabetically: " + sortedByName.get(sortedByName.size() - 1).getName());
        }
    }
    
    /**
     * Demonstrate lambda expressions for sorting
     */
    private void demonstrateLambdaSorting() {
        System.out.println("1. Sorting by age (ascending) using lambda:");
        ArrayList<Employee> sortedByAge = new ArrayList<>(employees);
        Collections.sort(sortedByAge, (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        
        for (Employee emp : sortedByAge) {
            System.out.println(emp);
        }
        
        System.out.println("\n2. Sorting by salary (ascending) using lambda:");
        ArrayList<Employee> sortedBySalaryAsc = new ArrayList<>(employees);
        Collections.sort(sortedBySalaryAsc, (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        
        for (Employee emp : sortedBySalaryAsc) {
            System.out.println(emp);
        }
        
        System.out.println("\n3. Sorting by name length using lambda:");
        ArrayList<Employee> sortedByNameLength = new ArrayList<>(employees);
        Collections.sort(sortedByNameLength, (e1, e2) -> Integer.compare(e1.getName().length(), e2.getName().length()));
        
        for (Employee emp : sortedByNameLength) {
            System.out.println(emp);
        }
    }
    
    /**
     * Demonstrate sorting by multiple fields
     */
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
        
        System.out.println("Employees sorted by age (ascending), then by salary (ascending):");
        System.out.println("Name                 | Age | Salary");
        System.out.println("-".repeat(50));
        
        for (Employee emp : sortedByMultiple) {
            System.out.println(emp);
        }
        
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
    
    /**
     * Demonstrate sorting without modifying the original list
     */
    private void sortWithoutModifyingOriginal() {
        System.out.println("Original list (unchanged):");
        displayEmployees();
        
        // Create a new sorted list without modifying the original
        List<Employee> sortedList = employees.stream()
            .sorted(new SalaryComparator())
            .toList();
        
        System.out.println("\nNew sorted list (original unchanged):");
        System.out.println("Name                 | Age | Salary");
        System.out.println("-".repeat(50));
        
        for (Employee emp : sortedList) {
            System.out.println(emp);
        }
        
        // Verify original list is unchanged
        System.out.println("\nVerification - Original list is still unchanged:");
        System.out.println("Name                 | Age | Salary");
        System.out.println("-".repeat(50));
        
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
