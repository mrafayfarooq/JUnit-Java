import domain.Department;
import domain.Employee;
import domain.SalesEmployee;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.BadParameterException;
import utils.NullParameterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Created by Muhammad Rafay on 5/31/17.
 */
public class DepartmentTest {
    private Department d;
    private Employee e;
    private SalesEmployee s;
    @Before
    public void setUp() {
        try {
            d = new Department("Sales");
            e = new Employee("Alan", "Conte", 6642, 35.75, new int[]{7, 6, 7, 8, 8});
        } catch (NullParameterException e) {
            fail("Creation of test Department or Employee object in @Before 'setUp' failed: " + e.getMessage());
        } catch (BadParameterException e) {
            fail("Creation of test Department or Employee object in @Before 'setUp' failed: " + e.getMessage());
        }
    }
    @Test
    public void testDepartmentName() {
        assertEquals(d.getDepartmentName(), "Sales");
    }

    @Test
    public void testAddEmployee() throws NullParameterException, BadParameterException {
        d.addEmployee(e);
        assertEquals(d.getNumInDepartment(), 1);
    }


    @Test
    public void testRemoveEmployee() throws NullParameterException, BadParameterException {
        d.removeEmployee(6642);
        assertEquals(d.getNumInDepartment(),0);
    }
    @Test
    public void testInDepartment() throws NullParameterException, BadParameterException {
        assertEquals(false, d.isInDepartment(6642));
        assertEquals(false, d.isInDepartment(-1));
    }

    @Rule
    public final ExpectedException badException = ExpectedException.none();

    @Test
    public void testAddEmployeeBadParameterException() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        for(int i=0;i<=19;i++) {
            d.addEmployee(e);
        }
        d.addEmployee(e);
    }

    @Rule
    public final ExpectedException nullException = ExpectedException.none();

    @Test
    public void testAddEmployeeNullException() throws NullParameterException, BadParameterException {
        e = null;
        nullException.expect(NullParameterException.class);
        d.addEmployee(e);
    }

}