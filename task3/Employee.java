public class Employee {
    private String name;
    private int age;
    private double salary;
    
    /**
     * Constructor for Employee class
     * @param name Employee name
     * @param age Employee age
     * @param salary Employee salary
     */
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    /**
     * toString method for displaying employee information
     * @return Formatted string representation of employee
     */
    @Override
    public String toString() {
        return String.format("Name: %-20s | Age: %2d | Salary: $%,10.2f", name, age, salary);
    }
}
