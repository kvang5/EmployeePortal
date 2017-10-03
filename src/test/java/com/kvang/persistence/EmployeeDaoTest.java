package com.kvang.persistence;

import com.kvang.entity.Employee;
import com.kvang.entity.State;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kvang on 9/21/17.
 */
public class EmployeeDaoTest {

    private final Logger logger = Logger.getLogger(this.getClass());
    StateDao stateDao;
    State state;

    EmployeeDao employeeDao;
    Employee employee;

    int newState = 0;
    int newEmployee = 0;

    @Before
    public void setUp() throws Exception {
        stateDao = new StateDao();

        state = new State();
        state.setState_code("ET");
        state.setState_name("Employee Testing");

        employeeDao = new EmployeeDao();

        employee = new Employee();
        employee.setFirst_name("Kyle");
        employee.setLast_name("Vang");
        employee.setAddress1("123 Some Street");
        employee.setCity("Madison");
        employee.setPostal_zip_code("53590");
        employee.setEmail("kvang5@madisoncollege.edu");
        employee.setHome_phone("828-455-6682");
        employee.setMobile_phone("828-455-6682");
        employee.setState(state);

    }


    @After
    public void tearDown() throws Exception {
        /*if (newState != 0) {
            stateDao.deleteState(newState);
        }

        if (newEmployee != 0) {
            employeeDao.deleteEmployee(newEmployee);
        }*/


    }

    @Test
    public void getAllEmployees() throws Exception {
        newState = stateDao.addState(state);
        newEmployee = employeeDao.addEmployee(employee);
        List<Employee> employees = employeeDao.getAllEmployees();
        assertTrue(employees.size() > 0);
    }

    @Test
    public void getEmployeeById() throws Exception {
        newState = stateDao.addState(state);
        newEmployee = employeeDao.addEmployee(employee);

        assertNotEquals("No employee returned", 0, newEmployee);
        assertEquals("Employee Id not returned correctly", employee.getEmployeeId(), employeeDao.getEmployeeById(newEmployee).getEmployeeId());
        assertEquals("Employee first name not returned correctly", employee.getFirst_name(), employeeDao.getEmployeeById(newEmployee).getFirst_name());
        assertEquals("Employee last name not returned correctly", employee.getLast_name(), employeeDao.getEmployeeById(newEmployee).getLast_name());
        assertEquals("Employee address1 not returned correctly", employee.getAddress1(), employeeDao.getEmployeeById(newEmployee).getAddress1());
        assertEquals("Employee address2 not returned correctly", employee.getAddress2(), employeeDao.getEmployeeById(newEmployee).getAddress2());
        assertEquals("Employee city not returned correctly", employee.getCity(), employeeDao.getEmployeeById(newEmployee).getCity());
        assertEquals("Employee postal zip code not returned correctly", employee.getPostal_zip_code(), employeeDao.getEmployeeById(newEmployee).getPostal_zip_code());
        assertEquals("Employee email not returned correctly", employee.getEmail(), employeeDao.getEmployeeById(newEmployee).getEmail());
        assertEquals("Employee home phone not returned correctly", employee.getHome_phone(), employeeDao.getEmployeeById(newEmployee).getHome_phone());
        assertEquals("Employee mobile phone not returned correctly", employee.getMobile_phone(), employeeDao.getEmployeeById(newEmployee).getMobile_phone());
        assertEquals("Employee state not returned correctly", employee.getState().getState_code(), employeeDao.getEmployeeById(newEmployee).getState().getState_code());
    }

    @Test
    public void addEmployee() throws Exception {
        newState = stateDao.addState(state);
        logger.info("newState: " + newState);
        newEmployee = employeeDao.addEmployee(employee);

        assertNotEquals("No new employee added", 0, newEmployee);
        assertEquals("Employee Id not returned correctly", employee.getEmployeeId(), employeeDao.getEmployeeById(newEmployee).getEmployeeId());
        assertEquals("Employee first name not returned correctly", employee.getFirst_name(), employeeDao.getEmployeeById(newEmployee).getFirst_name());
        assertEquals("Employee last name not returned correctly", employee.getLast_name(), employeeDao.getEmployeeById(newEmployee).getLast_name());
        assertEquals("Employee address1 not returned correctly", employee.getAddress1(), employeeDao.getEmployeeById(newEmployee).getAddress1());
        assertEquals("Employee address2 not returned correctly", employee.getAddress2(), employeeDao.getEmployeeById(newEmployee).getAddress2());
        assertEquals("Employee city not returned correctly", employee.getCity(), employeeDao.getEmployeeById(newEmployee).getCity());
        assertEquals("Employee postal zip code not returned correctly", employee.getPostal_zip_code(), employeeDao.getEmployeeById(newEmployee).getPostal_zip_code());
        assertEquals("Employee email not returned correctly", employee.getEmail(), employeeDao.getEmployeeById(newEmployee).getEmail());
        assertEquals("Employee home phone not returned correctly", employee.getHome_phone(), employeeDao.getEmployeeById(newEmployee).getHome_phone());
        assertEquals("Employee mobile phone not returned correctly", employee.getMobile_phone(), employeeDao.getEmployeeById(newEmployee).getMobile_phone());
        assertEquals("Employee state not returned correctly", employee.getState().getState_code(), employeeDao.getEmployeeById(newEmployee).getState().getState_code());
    }

    @Test
    public void deleteEmployee() throws Exception {
        stateDao.addState(state);
        employeeDao.addEmployee(employee);
        employeeDao.deleteEmployee(employee.getEmployeeId());
        assertNull("Employee not deleted", employeeDao.getEmployeeById(employee.getEmployeeId()));

    }

    @Test
    public void updateEmployee() throws Exception {
        newState = stateDao.addState(state);
        state.setStateId(stateDao.getStateById(55).getStateId());
        state.setState_code(stateDao.getStateById(55).getState_code());
        state.setState_name(stateDao.getStateById(55).getState_name());

        newEmployee = employeeDao.addEmployee(employee);
        employee.setFirst_name("Peyton");
        employee.setLast_name("Vang");
        employee.setAddress1("2020 Peyton Ln");
        employee.setAddress2("Apt. 2");
        employee.setCity("Sun Prairie");
        employee.setPostal_zip_code("53590");
        employee.setEmail("pvang08@madisoncollege.edu");
        employee.setHome_phone("111-111-6682");
        employee.setMobile_phone("111-415-6682");
        employee.setState(state);

        employeeDao.updateEmployee(employee);

        assertNotNull("State is null: " + state);
        assertNotNull("Employee is null: " + employee);
        assertEquals("Employee first name not updated", employee.getFirst_name(), employeeDao.getEmployeeById(newEmployee).getFirst_name());
        assertEquals("Employee last name not updated", employee.getLast_name(), employeeDao.getEmployeeById(newEmployee).getLast_name());
        assertEquals("Employee address1 name not updated", employee.getAddress1(), employeeDao.getEmployeeById(newEmployee).getAddress1());
        assertEquals("Employee address2 name not updated", employee.getAddress2(), employeeDao.getEmployeeById(newEmployee).getAddress2());
        assertEquals("Employee city name not updated", employee.getCity(), employeeDao.getEmployeeById(newEmployee).getCity());
        assertEquals("Employee postal zip code name not updated", employee.getPostal_zip_code(), employeeDao.getEmployeeById(newEmployee).getPostal_zip_code());
        assertEquals("Employee email name not updated", employee.getEmail(), employeeDao.getEmployeeById(newEmployee).getEmail());
        assertEquals("Employee home phone name not updated", employee.getHome_phone(), employeeDao.getEmployeeById(newEmployee).getHome_phone());
        assertEquals("Employee mobile phone name not updated", employee.getMobile_phone(), employeeDao.getEmployeeById(newEmployee).getMobile_phone());
        assertEquals("Employee state not updated", employee.getState().getState_code(), employeeDao.getEmployeeById(newEmployee).getState().getState_code());

    }
}