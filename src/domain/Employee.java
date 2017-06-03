/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import utils.BadParameterException;
import utils.NullParameterException;

/**
 *
 * @author hieldc
 */
public class Employee implements Comparable<Employee> {

    private String firstName;
    private String lastName;
    private int employeeId;
    private double hourlyRate;
    private Timecard timecard;

    /**+
     * Constructor for Employee
     * @param fName first name of Employee
     * @param lName last name
     * @param eId   Employee ID
     * @param hRate Hourly Rate
     * @param daysIn  Time Card Entries
     * @throws NullParameterException if any parameter is empty
     * @throws BadParameterException if the list contain maximum employees
     */
    public Employee(String fName, String lName, int eId, double hRate, int[] daysIn) throws NullParameterException, BadParameterException {
        setFirstName(fName);
        setLastName(lName);
        setEmployeeId(eId);
        setHourlyRate(hRate);
        setTimecard(new Timecard(daysIn));
    }

    /**+
     * Another parametrized constructor for Employee
     * @param e Employee Object
     * @throws NullParameterException if any parameter is empty
     * @throws BadParameterException if the list contain maximum employees
     */
    public Employee(Employee e) throws NullParameterException, BadParameterException {
        setFirstName(e.getFirstName());
        setLastName(e.getLastName());
        setEmployeeId(e.getEmployeeId());
        setHourlyRate(e.getHourlyRate());
        setTimecard(new Timecard(e.getTimecard()));
    }

    /**+
     * Overriding Java Comparable function to determine if Employee exist by comparing the ID
     * @param eIn Employee Object
     * @return -1 or 1 if false, 0 if true.
     */
    @Override
    public int compareTo(Employee eIn) {
        if (getEmployeeId() < eIn.getEmployeeId()) {
            return -1;
        } else if (getEmployeeId() == eIn.getEmployeeId()) {
            return 0;
        } else {
            return 1;
        }
    }

    /**+
     * Function to calculate Weekly Pay
     * @return weekly pay
     */
    public double getWeeklyPay() {
        return getTimecard().getWeeklyHours() * getHourlyRate();
    }

    /**+
     * Function to get the First Name of Employee
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**+
     * Set First Name of Employee
     * @param fName First Name
     * @throws NullParameterException if string is null
     * @throws BadParameterException if name is more than 20 character long
     */
    public final void setFirstName(String fName) throws NullParameterException, BadParameterException {
        if (fName == null) {
            throw new NullParameterException("Null value passed in for firstName");
        }

        if (fName.length() <= 0 || fName.length() > 20) {
            throw new BadParameterException("Bad value passed in for firstName: " + fName);
        }
        firstName = fName;
    }

    // Get Last Name
    public String getLastName() {
        return lastName;
    }

    /**+
     * Set Last Name of Employee
     * @param lName Lase Name
     * @throws NullParameterException if string is null
     * @throws BadParameterException if name is more than 20 character long
     */
    public final void setLastName(String lName) throws NullParameterException, BadParameterException {
        if (lName == null) {
            throw new NullParameterException("Null value passed in for lastName");
        }

        if (lName.length() <= 0 || lName.length() > 20) {
            throw new BadParameterException("Bad value passed in for lastName: " + lName);
        }
        lastName = lName;
    }

    /**+
     * Get Employee ID
     * @return employee id
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**+
     * Setting Employee ID after checking if it's valid or not.
     * @param eId Employee ID
     * @throws BadParameterException if Employee iD is less than or greater than 4 digits
     */
    public final void setEmployeeId(int eId) throws BadParameterException {
        if (eId < 1000 || eId > 9999) {
            throw new BadParameterException("Bad value passed in for employeeId: " + eId);
        }
        employeeId = eId;
    }

    /**+
     * Get Hourly Rate
     * @return hourly rate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**+
     * Set Hourly Rate
     * @param hRate hourly rate in double
     * @throws BadParameterException If Hourly Rate passed is less than or equal to 0
     */
    public final void setHourlyRate(double hRate) throws BadParameterException {
        if (hRate <= 0.0) {
            throw new BadParameterException("Bad value passed in for hourlyRate: " + hRate);
        }
        hourlyRate = hRate;
    }

    /**+
     * Get Time Card Entries
     * @return Timecard Object
     */
    private Timecard getTimecard() {

        return timecard;
    }

    /**+
     * Setting Time Card Entries
     * @param tCard time Card Object
     * @throws NullParameterException if timeCard entries have null vaue.
     */
    private void setTimecard(Timecard tCard) throws NullParameterException {
        if (tCard == null) {
            throw new NullParameterException("Null Timecard passed to setTimecard");
        }
        timecard = tCard;
    }

    /**+
     * Function to print pretty output.
     * It will print complete Details of the employee.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s %s %s%n", "Name:", getFirstName(), getLastName()));
        sb.append(String.format("%-20s %d%n", "Id:", getEmployeeId()));
        sb.append(String.format("%-20s $%.2f%n", "Hourly Rate:", getHourlyRate()));
        sb.append(String.format("%s", getTimecard()));
        sb.append(String.format("%-20s $%.2f%n", "Weekly Pay:", getWeeklyPay()));

        return sb.toString();
    }
}
