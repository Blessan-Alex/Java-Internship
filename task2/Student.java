public class Student {
    private int id;
    private String name;
    private double grade;
    
    /**
     * Constructor for Student class
     * @param id Student ID
     * @param name Student name
     * @param grade Student grade
     */
    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getGrade() {
        return grade;
    }
    
    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    /**
     * toString method for displaying student information
     * @return Formatted string representation of student
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-20s | Grade: %.2f", id, name, grade);
    }
}
