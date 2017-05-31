import domain.SalesEmployee;
import org.junit.Before;
import utils.BadParameterException;
import utils.NullParameterException;

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
}
