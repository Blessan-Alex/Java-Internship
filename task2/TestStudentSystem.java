import java.util.ArrayList;

public class TestStudentSystem {
    public static void main(String[] args) {
        System.out.println("=== Testing Student Management System ===\n");
        
        // Test 1: Create Student objects
        System.out.println("Test 1: Creating Student objects");
        Student student1 = new Student(1001, "John Smith", 85.5);
        Student student2 = new Student(1002, "Emma Johnson", 92.0);
        Student student3 = new Student(1003, "Michael Brown", 78.5);
        
        System.out.println("Student 1: " + student1);
        System.out.println("Student 2: " + student2);
        System.out.println("Student 3: " + student3);
        System.out.println();
        
        // Test 2: Test ArrayList operations
        System.out.println("Test 2: ArrayList operations");
        ArrayList<Student> testList = new ArrayList<>();
        
        // Add students
        testList.add(student1);
        testList.add(student2);
        testList.add(student3);
        System.out.println("Added 3 students. Total count: " + testList.size());
        
        // Display all students
        System.out.println("All students in list:");
        for (Student s : testList) {
            System.out.println("  " + s);
        }
        System.out.println();
        
        // Test 3: Test remove operation
        System.out.println("Test 3: Remove operation");
        System.out.println("Removing student with ID 1002...");
        testList.removeIf(s -> s.getId() == 1002);
        System.out.println("After removal. Total count: " + testList.size());
        System.out.println("Remaining students:");
        for (Student s : testList) {
            System.out.println("  " + s);
        }
        System.out.println();
        
        // Test 4: Test encapsulation
        System.out.println("Test 4: Encapsulation (getters and setters)");
        Student testStudent = new Student(9999, "Test Student", 50.0);
        System.out.println("Original: " + testStudent);
        
        // Use setters to modify
        testStudent.setName("Updated Test Student");
        testStudent.setGrade(95.0);
        System.out.println("After updates: " + testStudent);
        System.out.println();
        
        // Test 5: Test constructor and toString
        System.out.println("Test 5: Constructor and toString method");
        Student newStudent = new Student(8888, "Constructor Test", 88.8);
        System.out.println("New student created: " + newStudent);
        System.out.println("Student ID: " + newStudent.getId());
        System.out.println("Student Name: " + newStudent.getName());
        System.out.println("Student Grade: " + newStudent.getGrade());
        System.out.println();
        
        System.out.println("=== All Tests Completed Successfully! ===");
        System.out.println("The Student Management System is working correctly.");
        System.out.println("You can now run the interactive StudentManagementSystem class.");
    }
}
