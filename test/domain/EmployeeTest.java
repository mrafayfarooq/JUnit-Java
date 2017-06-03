package domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.BadParameterException;
import utils.NullParameterException;

import static org.junit.Assert.*;

/**
 * Created by Muhammad Rafay on 5/31/17.
 */
public class EmployeeTest {
    private Employee e;

    @Before
    public void setUp() {
        try {
            e = new Employee("Gina", "Thomas", 1547, 37.87, new int[]{8, 8, 8, 8, 7});
        } catch (NullParameterException e) {
            fail("Creation of Employee object in @Before 'setUp' failed: " + e.getMessage());
        } catch (BadParameterException e) {
            fail("Creation of Employee object in @Before 'setUp' failed: " + e.getMessage());
        }
    }
    @Rule
    public final ExpectedException badException = ExpectedException.none();

    @Test
    public void testIfEmployeeExist() {
        assertEquals(0,e.compareTo(e));
        Employee  emptyEmployee = null;
        badException.expect(NullPointerException.class);
        e.compareTo(emptyEmployee);
    }

    @Test
    public void testWeeklyPay() {
        assertEquals(1476.92, e.getWeeklyPay(), 0.1);
        assertNotEquals(0, e.getWeeklyPay());
    }

    @Test
    public void testFirstName() {
        assertEquals("Gina", e.getFirstName());
        assertNotEquals("Muhammad", e.getFirstName());
    }
    @Test
    public void testGetEmployeeID() {
        assertNotEquals("-1", e.getEmployeeId());
        assertEquals(1547, e.getEmployeeId());
    }

    @Test
    public void testGetHourlyRate() {
        assertNotEquals("-1", e.getHourlyRate());
        assertEquals(37.87, e.getHourlyRate(),0.1);
    }

    @Test
    public void testBadName() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        e = new Employee("", "", 1547, 37.87, new int[]{8, 8, 8, 8, 7});
    }
    @Test
    public void testNullLastName() throws NullParameterException, BadParameterException {
        badException.expect(NullParameterException.class);
        e = new Employee(null, "Something", 1547, 37.87, new int[]{8, 8, 8, 8, 7});
    }
    @Test
    public void testNullFirstName() throws NullParameterException, BadParameterException {
        badException.expect(NullParameterException.class);
        e = new Employee("Something", null, 1547, 37.87, new int[]{8, 8, 8, 8, 7});
    }
    @Test
    public void testBadEmployeeID() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        e = new Employee("Muhammad", "Rafay", -121, 37.87, new int[]{8, 8, 8, 8, 7});
    }
    @Test
    public void testBadHourlyRate() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        e = new Employee("Muhammad", "Rafay", 1111, 0, new int[]{8, 8, 8, 8, 7});
    }
    @Test
    public void testSetTimeCard() throws NullParameterException, BadParameterException {
        badException.expect(NullParameterException.class);
        e = new Employee("Muhammad", "Rafay", 1111, 37.87, null);
    }
    @Test
    public void testVeryLongFirstName() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        e = new Employee("This is more than 20 character long.", "Rafay", 1111, 37.87, null);
    }
    @Test
    public void testVeryLongLastName() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        e = new Employee("Muhammad", "This is more than 20 character long.", 1111, 37.87, null);
    }
    @Test
    public void testToString() {
        assertNotNull(e.toString());
    }

}
