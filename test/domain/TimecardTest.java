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
public class TimecardTest {
    private Timecard t;

    @Before
    public void setUp() {
        try {
            t = new Timecard(new int[]{6, 9, 7, 8, 8});
        } catch (NullParameterException e) {
            fail("Creation of Timecard object in @Before 'setUp' failed: " + e.getMessage());
        } catch (BadParameterException e) {
            fail("Creation of Timecard object in @Before 'setUp' failed: " + e.getMessage());
        }
    }

    @Test
    public void testGetWeeklyHours() {
        assertNotEquals(11, t.getWeeklyHours());
        assertEquals(38, t.getWeeklyHours());
    }
    @Test
    public void testGetHoursByDay() throws BadParameterException {
        assertNotEquals(t.getHoursByDay(3), 7);
        assertEquals(t.getHoursByDay(3), 8);
    }
    @Rule
    public final ExpectedException badException = ExpectedException.none();

    @Test
    public void testBadTimeCard() throws BadParameterException, NullParameterException {
        badException.expect(BadParameterException.class);
        t = new Timecard(new int[]{0, -1, 8, 8});
    }
    @Test
    public void testBadWeeklyHours() throws BadParameterException, NullParameterException {
        badException.expect(BadParameterException.class);
        t = new Timecard(new int[]{8, 8, 8, 8,1,1});
    }
    @Test
    public void testBadString() throws BadParameterException, NullParameterException {
        badException.expect(BadParameterException.class);
        t = new Timecard(new int[]{-1, 8, 8,1,1});
    }
    @Test
    public void testBadHoursByDay() throws BadParameterException, NullParameterException {
        badException.expect(BadParameterException.class);
        t.getHoursByDay(-1);
    }
    @Test
    public void testToString() throws BadParameterException, NullParameterException {
        t = new Timecard(new int[]{8, 8, 8, 8,8});
        assertNotNull(t.toString());
    }
}


