import java.util.Comparator;

/**
 * Comparator to sort employees by salary in descending order (highest to lowest)
 */
public class SalaryComparator implements Comparator<Employee> {
    
    /**
     * Compares two employees by salary in descending order
     * @param emp1 First employee
     * @param emp2 Second employee
     * @return Negative if emp1 salary > emp2 salary, positive if emp1 salary < emp2 salary, 0 if equal
     */
    @Override
    public int compare(Employee emp1, Employee emp2) {
        // Sort by salary in descending order (highest first)
        // Using Double.compare for proper handling of NaN and infinity values
        return Double.compare(emp2.getSalary(), emp1.getSalary());
        
        // Alternative approach (if you want to handle null values):
        // if (emp1 == null && emp2 == null) return 0;
        // if (emp1 == null) return 1;
        // if (emp2 == null) return -1;
        // return Double.compare(emp2.getSalary(), emp1.getSalary());
    }
}
