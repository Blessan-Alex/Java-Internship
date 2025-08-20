import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabaseManager {
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASS = "";
    
    public static void main(String[] args) {
        UserDatabaseManager manager = new UserDatabaseManager();
        
        System.out.println("=== JDBC Database Integration Demo ===\n");
        
        try {
            // Step 1: Create database connection
            System.out.println("1. Connecting to database...");
            Connection conn = manager.getConnection();
            System.out.println("✅ Database connected successfully!");
            
            // Step 2: Create users table
            System.out.println("\n2. Creating users table...");
            manager.createTable(conn);
            System.out.println("✅ Users table created successfully!");
            
            // Step 3: Insert sample users
            System.out.println("\n3. Inserting sample users...");
            manager.insertUser(conn, "John Doe", "john@example.com");
            manager.insertUser(conn, "Jane Smith", "jane@example.com");
            manager.insertUser(conn, "Bob Johnson", "bob@example.com");
            System.out.println("✅ Sample users inserted successfully!");
            
            // Step 4: Display all users
            System.out.println("\n4. Displaying all users...");
            List<User> users = manager.getAllUsers(conn);
            manager.displayUsers(users);
            
            // Step 5: Update a user
            System.out.println("\n5. Updating user...");
            manager.updateUser(conn, 1, "John Updated", "john.updated@example.com");
            System.out.println("✅ User updated successfully!");
            
            // Step 6: Display updated users
            System.out.println("\n6. Displaying updated users...");
            users = manager.getAllUsers(conn);
            manager.displayUsers(users);
            
            // Step 7: Delete a user
            System.out.println("\n7. Deleting user...");
            manager.deleteUser(conn, 3);
            System.out.println("✅ User deleted successfully!");
            
            // Step 8: Final display
            System.out.println("\n8. Final user list...");
            users = manager.getAllUsers(conn);
            manager.displayUsers(users);
            
            System.out.println("\n🎉 All JDBC operations completed successfully!");
            
        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Get database connection
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
    /**
     * Create users table
     */
    private void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL)";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
    
    /**
     * Insert a new user using PreparedStatement
     */
    private void insertUser(Connection conn, String name, String email) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("  ✅ Inserted: " + name + " (" + email + ")");
        }
    }
    
    /**
     * Get all users from database
     */
    private List<User> getAllUsers(Connection conn) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, email FROM users";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                users.add(new User(id, name, email));
            }
        }
        
        return users;
    }
    
    /**
     * Update user by ID
     */
    private void updateUser(Connection conn, int id, String name, String email) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("  ✅ Updated user ID " + id + " to: " + name + " (" + email + ")");
        }
    }
    
    /**
     * Delete user by ID
     */
    private void deleteUser(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("  ✅ Deleted user ID " + id);
        }
    }
    
    /**
     * Display users in formatted table
     */
    private void displayUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        
        System.out.println("ID | Name           | Email");
        System.out.println("-".repeat(50));
        
        for (User user : users) {
            System.out.println(user);
        }
        
        System.out.println("Total users: " + users.size());
    }
}
