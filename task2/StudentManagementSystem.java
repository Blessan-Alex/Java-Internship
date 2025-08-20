import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    private ArrayList<Student> students;
    private Scanner scanner;
    
    /**
     * Constructor initializes the student list and scanner
     */
    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Main method to run the Student Management System
     */
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.run();
    }
    
    /**
     * Main program loop with menu system
     */
    public void run() {
        boolean running = true;
        
        System.out.println("=== Student Management System ===");
        System.out.println("Welcome to the Student Management System!");
        
        while (running) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    displayStudents();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the Student Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu options
     */
    private void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Display All Students");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }
    
    /**
     * Gets and validates user menu choice
     * @return Valid menu choice (1-4)
     */
    private int getMenuChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number (1-4).");
            scanner.next(); // consume invalid input
        }
        return scanner.nextInt();
    }
    
    /**
     * Adds a new student to the system
     */
    public void addStudent() {
        System.out.println("\n--- Add New Student ---");
        
        // Get Student ID
        System.out.print("Enter Student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid ID number.");
            scanner.next();
        }
        int id = scanner.nextInt();
        
        // Check if ID already exists
        if (findStudentById(id) != null) {
            System.out.println("Error: Student with ID " + id + " already exists!");
            return;
        }
        
        // Get Student Name
        scanner.nextLine(); // consume newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty!");
            return;
        }
        
        // Get Student Grade
        System.out.print("Enter Student Grade (0.0 - 100.0): ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please enter a valid grade number.");
            scanner.next();
        }
        double grade = scanner.nextDouble();
        
        if (grade < 0.0 || grade > 100.0) {
            System.out.println("Error: Grade must be between 0.0 and 100.0!");
            return;
        }
        
        // Create and add student
        Student newStudent = new Student(id, name, grade);
        students.add(newStudent);
        System.out.println("Student added successfully!");
        System.out.println("Added: " + newStudent);
    }
    
    /**
     * Removes a student from the system by ID
     */
    public void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        
        if (students.isEmpty()) {
            System.out.println("No students to remove. The list is empty.");
            return;
        }
        
        System.out.print("Enter Student ID to remove: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid ID number.");
            scanner.next();
        }
        int id = scanner.nextInt();
        
        Student studentToRemove = findStudentById(id);
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student removed successfully!");
            System.out.println("Removed: " + studentToRemove);
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }
    
    /**
     * Displays all students in the system
     */
    public void displayStudents() {
        System.out.println("\n--- All Students ---");
        
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        
        System.out.println("Total Students: " + students.size());
        System.out.println("ID   | Name                 | Grade");
        System.out.println("-----|----------------------|-------");
        
        for (Student student : students) {
            System.out.println(student);
        }
    }
    
    /**
     * Finds a student by ID
     * @param id Student ID to search for
     * @return Student object if found, null otherwise
     */
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * Adds sample data for testing purposes
     */
    public void addSampleData() {
        students.add(new Student(1001, "John Smith", 85.5));
        students.add(new Student(1002, "Emma Johnson", 92.0));
        students.add(new Student(1003, "Michael Brown", 78.5));
        students.add(new Student(1004, "Sarah Davis", 95.0));
        students.add(new Student(1005, "David Wilson", 88.5));
        System.out.println("Sample data added successfully!");
    }
}
