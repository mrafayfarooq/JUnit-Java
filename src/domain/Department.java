
package domain;

import java.util.ArrayList;
import java.util.Collections;
import utils.BadParameterException;
import utils.NullParameterException;

public class Department {

    private String departmentName;
    public static final int MAX_EMP = 20;
    private final ArrayList<Employee> employeeList = new ArrayList<>(0); // Array List of type EMployee to hold the list of Employees

    /**+
     * Constructor of the Class setting Department Name
     * @param dName name of the department
     * @throws NullParameterException if department name is null
     * @throws BadParameterException if name is empty
     */
    public Department(String dName) throws NullParameterException, BadParameterException {
        setDepartmentName(dName);
    }

    /**+
     * Get Department Name
     * @return department name
     */

    public String getDepartmentName() {
        return departmentName;
    }

    /**+
     * This method is called by Constructor of this class to set the Department Name.
     * @param dNameIn department name
     * @throws NullParameterException if name is null
     * @throws BadParameterException if name is empty
     */

    private void setDepartmentName(String dNameIn) throws NullParameterException, BadParameterException {
        if (dNameIn == null) {
            throw new NullParameterException("Null value passed in for departmentName");
        }

        if (dNameIn.isEmpty()) {
            throw new BadParameterException("Invalid Department Name: " + dNameIn);
        }
        departmentName = dNameIn;
    }

    /**+
     * Get Employee List
     * @return list of employees
     */
    private ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    /**+
     * Add Employee into Employee List after validation
     * @param e employee object
     * @throws NullParameterException if object is null
     * @throws BadParameterException if the size of list is MAX_EMP
     */
    public void addEmployee(Employee e) throws NullParameterException, BadParameterException {
        if (e == null) {
            throw new NullParameterException("Null Employee sent to addEmployee!");
        }
        // Checking if Employee List size is greater than MAX_EMP = 20
        if (getEmployeeList().size() >= MAX_EMP) {
            throw new BadParameterException("This Department is already at the max. number of employees: " + MAX_EMP);
        }
        // Adding Employee Object into List
        getEmployeeList().add(new Employee(e));
        Collections.sort(getEmployeeList()); // Sorting the Array List
    }

    /**+
     * Add Employee of type Sales into the list
     * @param e Sales Employee Object
     * @throws NullParameterException if object is null
     * @throws BadParameterException if there are more than 20 Sales Employees into the list
     */
    public void addEmployee(SalesEmployee e) throws NullParameterException, BadParameterException {
        if (e == null) {
            throw new NullParameterException("Null Employee sent to addEmployee!");
        }
        if (getEmployeeList().size() > MAX_EMP) {
            throw new BadParameterException("This Department is already at the max. number of employees: " + MAX_EMP);
        }

        SalesEmployee ee = new SalesEmployee(e); // Create a SalesEmployee copy
        getEmployeeList().add(ee); // Add a sales employee
        Collections.sort(getEmployeeList()); // Sort the Array List

    }

    /**+
     * Remove Employee using ID
     * @param id id of employee
     * @return deleted Employeee Object or NULL Object
     */
    public Employee removeEmployee(int id) {
        // Traversing Employee List
        for (Employee e : getEmployeeList()) {
            if (e.getEmployeeId() == id) {
                Employee emp = e;   // Referencing to new Employee Object for Safe Delete
                getEmployeeList().remove(e); // Removing it from the list
                return emp;
            }
        }
        return null; // If no employee with id found
    }

    /**+
     * Check if Employee is in Department
     * @param id id of employee
     * @return true if id found else false.
     */

    public boolean isInDepartment(int id) {
        for (Employee e : getEmployeeList()) {
            if (e.getEmployeeId() == id) {
                return true;
            }
        }
        return false;
    }

    /**+
     * Get the size of department
     * @return size of department
     */
    public int getNumInDepartment() {
        return getEmployeeList().size();
    }

    /**+
     * Utility function to return String with
     * Department Name, Department Size and List of Employees.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Department: %s\n", getDepartmentName()));
        for (int i = 0; i < ("Department: " + getDepartmentName()).length(); i++) {
            sb.append("-");
        }
        sb.append("\nEmployees:\n");
        for (Employee e : getEmployeeList()) {
            sb.append(e + "\n");
        }

        return sb.toString();

    }
}
