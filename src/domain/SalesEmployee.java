
package domain;

import utils.BadParameterException;
import utils.NullParameterException;

public class SalesEmployee extends Employee {

    private double salesAmountNeeded;   // Target to achieve by Sales Employee
    private double weeklySalesTotal;    // Weekly SAles by the Sales Employee
    public static final double BONUS_MULTIPLIER = 0.25; // Bonus on Payment

    /**+
     * Sales Emplyee Constructor, similar to Emplyee Constuctor
     * @param fName First Name
     * @param lName Last Name
     * @param eId Employee ID
     * @param hRate Hourly Rate
     * @param daysIn Time Card Entries
     * @param salesNeeded Sales Target
     * @param weeklyTotal Total Sales for the whole week
     * @throws NullParameterException if any paramter is null
     * @throws BadParameterException if employee list contains maximum items
     */
    public SalesEmployee(String fName, String lName, int eId, double hRate, int[] daysIn, double salesNeeded, double weeklyTotal) throws NullParameterException, BadParameterException {
        super(fName, lName, eId, hRate, daysIn);
        setSalesAmountNeeded(salesNeeded);
        setWeeklySalesTotal(weeklyTotal);
    }

    /**+
     * Constructor which accept Employee Object and set it's Weekly Sales Target
     * and also set the sales amount needed for that Employee.
     * @param se Employee Object
     * @throws NullParameterException if Object is NULL
     * @throws BadParameterException if sales amount is equal or below zero.
     */

    public SalesEmployee(SalesEmployee se) throws NullParameterException, BadParameterException {
        super(se);
        setSalesAmountNeeded(se.getSalesAmountNeeded());
        setWeeklySalesTotal(se.getWeeklySalesTotal());
    }

    /**+
     * Get the Sales amount needed.
     * @return sale amount
     */
    public double getSalesAmountNeeded() {
        return salesAmountNeeded;
    }

    /**+
     * Get the Weekly Sales in Total
     * @return weekly sales
     */
    public double getWeeklySalesTotal() {
        return weeklySalesTotal;
    }

    /**+
     * Set the Sales Amounts Needed to complete the Target
     * @param d sales amount
     * @throws BadParameterException if amount is equal or below zero
     */
    private void setSalesAmountNeeded(double d) throws BadParameterException {
        if (d <= 0.0) {
            throw new BadParameterException("Invalid sales amount needed: " + d);
        }
        salesAmountNeeded = d;
    }

    /**+
     * Set the total number of sales done by the Sales Employee in a Week.
     * @param totalIn total sales
     * @throws BadParameterException if sales is less than 0.0
     */
    private void setWeeklySalesTotal(double totalIn) throws BadParameterException {
        if (totalIn <= 0.0) {
            throw new BadParameterException("Invalid weekly sales total: " + totalIn);
        }
        weeklySalesTotal = totalIn;
    }

    /**+
     * Get the Weekly Payment of Employee. This method is override
     * from Employee class as each type of Employee may have different
     * logic to calculate weekly payment.
     * @return weekly payment
     */

    @Override
    public double getWeeklyPay() {

        if (getWeeklySalesTotal() >= getSalesAmountNeeded()) {
            return getWeeklySalesTotal() * BONUS_MULTIPLIER;
        }

        return super.getWeeklyPay();
    }

    /**+
     * Print the summary of Total Sales Amount Needed vs Total Weekly Sales
     * @return string
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(String.format("%-20s $%.2f%n", "Sales Amount Needed:", getSalesAmountNeeded()));
        sb.append(String.format("%-20s $%.2f%n", "Weekly Sales Total:", getWeeklySalesTotal()));

        return sb.toString();
    }
}
