package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.BadParameterException;
import utils.NullParameterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by Muhammad Rafay on 5/31/17.
 */
public class TimecardTest {
    private Timecard t;
    @BeforeEach
    public void setUp() throws NullParameterException, BadParameterException {
        t = new Timecard(new int[]{6, 9, 7, 8, 8});
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

}