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
    private SalesEmployee s;

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
    public final ExpectedException nullException = ExpectedException.none();

    @Test
    public void testIfEmployeeExist() {
        assertEquals(0,e.compareTo(e));
        Employee  emptyEmployee = null;
        nullException.expect(NullPointerException.class);
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
}
