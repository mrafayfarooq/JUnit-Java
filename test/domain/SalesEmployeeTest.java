package domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.BadParameterException;
import utils.NullParameterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Created by Muhammad Rafay on 5/31/17.
 */
public class SalesEmployeeTest {
    private SalesEmployee s;
    @Before
    public void setUp() {
        try {
            s = new SalesEmployee("Raj", "Pandy", 5489, 36.90, new int[]{6, 9, 7, 8, 8}, 10000, 12250);
        } catch (NullParameterException e) {
            fail("Creation of SalesEmployee object in @Before 'setUp' failed: " + e.getMessage());
        } catch (BadParameterException e) {
            fail("Creation of SalesEmployee object in @Before 'setUp' failed: " + e.getMessage());
        }
    }
    @Rule
    public final ExpectedException badException = ExpectedException.none();

    @Test
    public void testBadSalesAmount() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        s = new SalesEmployee("Raj", "Pandy", 5489, 36.90, new int[]{6, 9, 7, 8, 8}, -1, 12250);
    }
    @Test
    public void testSetWeeklyTotal() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        s = new SalesEmployee("Raj", "Pandy", 5489, 36.90, new int[]{6, 9, 7, 8, 8}, 1000, -1);
    }
    @Test
    public void testGetSalesAmountNeeded()  {
        assertEquals(s.getSalesAmountNeeded(), 10000, 0.1);
        assertNotEquals(s.getSalesAmountNeeded(), 100, 0.1);

    }
    @Test
    public void testGetWeeklyTotal()  {
        assertEquals(s.getWeeklySalesTotal(), 12250, 0.1);
        assertNotEquals(s.getWeeklySalesTotal(), 100, 0.1);

    }
    @Test
    public void testGetWeeklySalesTotal() throws NullParameterException, BadParameterException {
        s = new SalesEmployee("Raj", "Pandy", 5489, 36.90, new int[]{6, 9, 7, 8, 8}, 10000, 8000);
        assertNotEquals(s.getWeeklySalesTotal(), 100, 0.1);

    }

}
