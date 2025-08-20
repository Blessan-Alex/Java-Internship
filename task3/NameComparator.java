import java.util.Comparator;

/**
 * Comparator to sort employees by name in ascending order (alphabetical)
 */
public class NameComparator implements Comparator<Employee> {
    
    /**
     * Compares two employees by name in ascending order
     * @param emp1 First employee
     * @param emp2 Second employee
     * @return Negative if emp1 name < emp2 name, positive if emp1 name > emp2 name, 0 if equal
     */
    @Override
    public int compare(Employee emp1, Employee emp2) {
        // Sort by name in ascending order (alphabetical)
        // Using String.compareToIgnoreCase for case-insensitive comparison
        return emp1.getName().compareToIgnoreCase(emp2.getName());
        
        // Alternative approach (case-sensitive comparison):
        // return emp1.getName().compareTo(emp2.getName());
        
        // Alternative approach (if you want to handle null values):
        // if (emp1 == null && emp2 == null) return 0;
        // if (emp1 == null) return -1;
        // if (emp2 == null) return 1;
        // if (emp1.getName() == null && emp2.getName() == null) return 0;
        // if (emp1.getName() == null) return -1;
        // if (emp2.getName() == null) return 1;
        // return emp1.getName().compareToIgnoreCase(emp2.getName());
    }
}
