import java.util.ArrayList;
import java.util.List;

/**
 * TaskControllerTest - Demonstrates Unit Testing Concepts
 * This example shows testing patterns for REST API endpoints without external dependencies
 */
public class TaskControllerTest {

    public static void main(String[] args) {
        System.out.println("=== Running Task Controller Unit Tests ===\n");
        
        TaskControllerTest testRunner = new TaskControllerTest();
        
        // Run all tests
        testRunner.testGetAllTasks_Success();
        testRunner.testGetTaskById_Success();
        testRunner.testGetTaskById_NotFound();
        testRunner.testCreateTask_Success();
        testRunner.testCreateTask_InvalidData();
        testRunner.testUpdateTask_Success();
        testRunner.testUpdateTask_NotFound();
        testRunner.testDeleteTask_Success();
        testRunner.testDeleteTask_NotFound();
        testRunner.testRepositoryException();
        
        System.out.println("\n=== All Tests Completed ===");
    }

    // Test GET all tasks - Success scenario
    public void testGetAllTasks_Success() {
        System.out.println("Test: GET all tasks - Success");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        mockRepository.save(task1);
        mockRepository.save(task2);

        // Act
        List<Task> result = taskController.getAllTasks();

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Should return 2 tasks");
        assertEquals("Task 1", result.get(0).getTitle(), "First task title should match");
        assertEquals("Task 2", result.get(1).getTitle(), "Second task title should match");
        
        System.out.println("✓ PASSED\n");
    }

    // Test GET task by ID - Success scenario
    public void testGetTaskById_Success() {
        System.out.println("Test: GET task by ID - Success");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);
        Task task = new Task("Test Task", "Test Description");
        Task savedTask = mockRepository.save(task);

        // Act
        Task result = taskController.getTaskById(savedTask.getId());

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals("Test Task", result.getTitle(), "Task title should match");
        assertEquals("Test Description", result.getDescription(), "Task description should match");
        
        System.out.println("✓ PASSED\n");
    }

    // Test GET task by ID - Not found scenario
    public void testGetTaskById_NotFound() {
        System.out.println("Test: GET task by ID - Not Found");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);

        // Act
        Task result = taskController.getTaskById(999L);

        // Assert
        assertNull(result, "Result should be null for non-existent task");
        
        System.out.println("✓ PASSED\n");
    }

    // Test POST create task - Success scenario
    public void testCreateTask_Success() {
        System.out.println("Test: POST create task - Success");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);
        Task newTask = new Task("New Task", "New Description");

        // Act
        Task result = taskController.createTask(newTask);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertNotNull(result.getId(), "Task should have an ID");
        assertEquals("New Task", result.getTitle(), "Task title should match");
        assertEquals("New Description", result.getDescription(), "Task description should match");
        
        System.out.println("✓ PASSED\n");
    }

    // Test POST create task - Invalid data scenario
    public void testCreateTask_InvalidData() {
        System.out.println("Test: POST create task - Invalid Data");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);
        Task invalidTask = new Task("", ""); // Empty title and description

        // Act & Assert
        try {
            taskController.createTask(invalidTask);
            fail("Should throw IllegalArgumentException for invalid data");
        } catch (IllegalArgumentException e) {
            assertEquals("Task title cannot be empty", e.getMessage(), "Error message should match");
        }
        
        System.out.println("✓ PASSED\n");
    }

    // Test PUT update task - Success scenario
    public void testUpdateTask_Success() {
        System.out.println("Test: PUT update task - Success");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);
        Task originalTask = new Task("Original Task", "Original Description");
        Task savedTask = mockRepository.save(originalTask);
        Task updateData = new Task("Updated Task", "Updated Description");

        // Act
        Task result = taskController.updateTask(savedTask.getId(), updateData);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals("Updated Task", result.getTitle(), "Task title should be updated");
        assertEquals("Updated Description", result.getDescription(), "Task description should be updated");
        
        System.out.println("✓ PASSED\n");
    }

    // Test PUT update task - Not found scenario
    public void testUpdateTask_NotFound() {
        System.out.println("Test: PUT update task - Not Found");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);
        Task updateData = new Task("Updated Task", "Updated Description");

        // Act
        Task result = taskController.updateTask(999L, updateData);

        // Assert
        assertNull(result, "Result should be null for non-existent task");
        
        System.out.println("✓ PASSED\n");
    }

    // Test DELETE task - Success scenario
    public void testDeleteTask_Success() {
        System.out.println("Test: DELETE task - Success");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);
        Task task = new Task("Task to Delete", "Will be deleted");
        Task savedTask = mockRepository.save(task);

        // Act
        boolean result = taskController.deleteTask(savedTask.getId());

        // Assert
        assertTrue(result, "Delete should return true");
        assertNull(mockRepository.findById(savedTask.getId()), "Task should be deleted from repository");
        
        System.out.println("✓ PASSED\n");
    }

    // Test DELETE task - Not found scenario
    public void testDeleteTask_NotFound() {
        System.out.println("Test: DELETE task - Not Found");
        
        // Arrange
        MockTaskRepository mockRepository = new MockTaskRepository();
        TaskController taskController = new TaskController(mockRepository);

        // Act
        boolean result = taskController.deleteTask(999L);

        // Assert
        assertFalse(result, "Delete should return false for non-existent task");
        
        System.out.println("✓ PASSED\n");
    }

    // Test exception handling
    public void testRepositoryException() {
        System.out.println("Test: Repository Exception Handling");
        
        // Arrange
        MockTaskRepository exceptionRepository = new MockTaskRepository();
        exceptionRepository.setThrowException(true);
        TaskController controllerWithException = new TaskController(exceptionRepository);

        // Act & Assert
        try {
            controllerWithException.getAllTasks();
            fail("Should throw RuntimeException for database error");
        } catch (RuntimeException e) {
            assertEquals("Database error", e.getMessage(), "Error message should match");
        }
        
        System.out.println("✓ PASSED\n");
    }

    // Simple assertion methods
    private void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw new AssertionError(message);
        }
    }

    private void assertNull(Object obj, String message) {
        if (obj != null) {
            throw new AssertionError(message);
        }
    }

    private void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + " - Expected: " + expected + ", Actual: " + actual);
        }
    }

    private void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }

    private void fail(String message) {
        throw new AssertionError(message);
    }

    // Mock classes for testing
    static class Task {
        private Long id;
        private String title;
        private String description;

        public Task(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    static class MockTaskRepository {
        private List<Task> tasks = new ArrayList<>();
        private Long nextId = 1L;
        private boolean throwException = false;

        public void setThrowException(boolean throwException) {
            this.throwException = throwException;
        }

        public Task save(Task task) {
            if (throwException) {
                throw new RuntimeException("Database error");
            }
            if (task.getId() == null) {
                task.setId(nextId++);
            }
            tasks.add(task);
            return task;
        }

        public List<Task> findAll() {
            if (throwException) {
                throw new RuntimeException("Database error");
            }
            return new ArrayList<>(tasks);
        }

        public Task findById(Long id) {
            if (throwException) {
                throw new RuntimeException("Database error");
            }
            return tasks.stream()
                    .filter(task -> task.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        public void deleteById(Long id) {
            if (throwException) {
                throw new RuntimeException("Database error");
            }
            tasks.removeIf(task -> task.getId().equals(id));
        }
    }

    static class TaskController {
        private MockTaskRepository repository;

        public TaskController(MockTaskRepository repository) {
            this.repository = repository;
        }

        public List<Task> getAllTasks() {
            return repository.findAll();
        }

        public Task getTaskById(Long id) {
            return repository.findById(id);
        }

        public Task createTask(Task task) {
            if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
                throw new IllegalArgumentException("Task title cannot be empty");
            }
            return repository.save(task);
        }

        public Task updateTask(Long id, Task taskData) {
            Task existingTask = repository.findById(id);
            if (existingTask == null) {
                return null;
            }
            existingTask.setTitle(taskData.getTitle());
            existingTask.setDescription(taskData.getDescription());
            return repository.save(existingTask);
        }

        public boolean deleteTask(Long id) {
            Task task = repository.findById(id);
            if (task == null) {
                return false;
            }
            repository.deleteById(id);
            return true;
        }
    }
}
