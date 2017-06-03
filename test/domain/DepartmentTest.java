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
public class DepartmentTest {
    private Department d;
    private Employee e;
    private SalesEmployee s;

    @Before
    public void setUp() {
        try {
            d = new Department("Sales");
            e = new Employee("Alan", "Conte", 6642, 35.75, new int[]{7, 6, 7, 8, 8});
            s = new SalesEmployee("Raj", "Pandy", 5489, 36.90, new int[]{6, 9, 7, 8, 8}, 10000, 12250);
            d.addEmployee(s);
            d.addEmployee(e);
        } catch (NullParameterException e) {
            fail("Creation of test Department or Employee object in @Before 'setUp' failed: " + e.getMessage());
        } catch (BadParameterException e) {
            fail("Creation of test Department or Employee object in @Before 'setUp' failed: " + e.getMessage());
        }
    }

    @Test
    public void testDepartmentName()  {
        assertEquals(d.getDepartmentName(), "Sales");
    }

    @Test
    public void testAddEmployee() throws NullParameterException, BadParameterException   {
        assertEquals(d.getNumInDepartment(), 2);
        d.addEmployee(e);
        assertEquals(d.getNumInDepartment(), 3);

    }
    @Test
    public void testRemoveEmployee() {
        d.removeEmployee(6642);
        assertEquals(d.getNumInDepartment(),1);
        d.removeEmployee(5489);
        assertEquals(d.getNumInDepartment(),0);
        d.removeEmployee(533);
        assertNotEquals(d.getNumInDepartment(),-1);

    }
    @Test
    public void testInDepartment() {
        assertEquals(true, d.isInDepartment(6642));
        assertEquals(false, d.isInDepartment(-1));
    }

    @Rule
    public final ExpectedException badException = ExpectedException.none();

    @Test
    public void testAddEmployeeBadException() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        for(int i=0;i<=19;i++) {
            d.addEmployee(e);
        }
        d.addEmployee(e);
    }
    @Test
    public void testAddSalesEmployeeBadException() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        for(int i=1;i<=20;i++) {
            d.addEmployee(s);
        }
        d.addEmployee(s);
    }
    @Test
    public void testSetDepartmentNameBadException() throws NullParameterException, BadParameterException {
        badException.expect(BadParameterException.class);
        d = new Department("");
    }

    @Test
    public void testAddEmployeeNullException() throws NullParameterException, BadParameterException {
        e = null;
        badException.expect(NullParameterException.class);
        d.addEmployee(e);
    }
    @Test
    public void testAddSalesEmployeeNullException() throws NullParameterException, BadParameterException {
        s = null;
        badException.expect(NullParameterException.class);
        d.addEmployee(s);
    }
    @Test
    public void testSetDepartmentNameNullException() throws NullParameterException, BadParameterException {
        badException.expect(NullParameterException.class);
        d = new Department(null);
    }

    @Test
    public void testToString() {
        assertNotNull(d.toString());
    }

}
