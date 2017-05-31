package domain;

import utils.BadParameterException;
import utils.NullParameterException;

/**+
 * This class is used to Manage Time Punched by Employees.
 *
 */
public class Timecard {

    public static final int NUM_DAYS = 5;   // Number of Days Employees work in a week
    private final int[] daysOfTheWeek = new int[NUM_DAYS]; // Collection of Number of Days

    /**+
     * Constructor for Time Card Class which takes an array of integers
     * @param daysIn Array List
     * @throws NullParameterException if array is null or empty
     * @throws BadParameterException if number of days passed are greater than 5
     */
    public Timecard(int[] daysIn) throws NullParameterException, BadParameterException {
        if (daysIn == null) {
            throw new NullParameterException("Null int array passed to Timecard c'tor");
        }
        // The timecard should have only 5 days
        if (daysIn.length != NUM_DAYS) {
            throw new BadParameterException("Invalid int array passed to Timecard c'tor, length: " + daysIn.length);
        }
        for (int i = 0; i < NUM_DAYS; i++) {
            setHoursByDay(i, daysIn[i]);
        }
    }

    /**+
     * Time Card Class Constructor with Time Card Object
     * @param t object of type time class
     * @throws BadParameterException if values passed in hoursByDay function is not between 1-24
     */
    public Timecard(Timecard t) throws BadParameterException {
        // Traversing all days and setting Days of the week
        for (int i = 0; i < NUM_DAYS; i++) {
            setHoursByDay(i, t.getHoursByDay(i));
        }
    }

    /**+
     * Get Weekly Hours by traversing all the working days
     * @return number of hours worked in a week
     */
    public int getWeeklyHours() {
        int count = 0;
        // Traversing all working days and counting all hours
        for (int i = 0; i < NUM_DAYS; i++) {
            try {
                count += getHoursByDay(i);  //Getting the total count of the week
            } catch (BadParameterException e) {
                System.err.println("Invalid day " + i + " skipped in summing weekly hours");
            }
        }
        return count;
    }

    /**+
     * Get number of hours worked in a day
     * @param day particular day of the week
     * @return number of hours worked in that particular day
     * @throws BadParameterException if number of day is not between 1-5
     */
    public int getHoursByDay(int day) throws BadParameterException {
        if (day < 0 || day >= NUM_DAYS) {
            throw new BadParameterException("Bad day value passed to getHoursByDay: " + day);
        }

        return daysOfTheWeek[day];
    }

    /**+
     * Setting Hours by Day
     * @param day particular day
     * @param hours number of hours worked on that day
     * @throws BadParameterException if day or hours are not valid
     */
    private void setHoursByDay(int day, int hours) throws BadParameterException {
        // Validation for number of Days
        if (day < 0 || day >= NUM_DAYS) {
            throw new BadParameterException("Bad day value passed to setHoursByDay: " + day);
        }
        // Validation for hours
        if (hours < 0 || hours > 24) {
            throw new BadParameterException("Bad hours value passed to setHoursByDay: " + hours);
        }
        daysOfTheWeek[day] = hours;
    }

    /**+
     * Print the Weekly Summary of Employee Time Card
     * @return String of weekly summary
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %d%n", "Weekly Hours:", getWeeklyHours()));
        for (int i = 0; i < NUM_DAYS; i++) {
            try {
                sb.append(String.format("%7s %d: %11d%n", "Day", (i + 1), getHoursByDay(i)));
            } catch (BadParameterException e) {
                sb.append(String.format("%7s %d: %s %d%n", "Day", (i + 1), "Error Accessing Hours for Day", i));
            }
        }
        return sb.toString();
    }
}
