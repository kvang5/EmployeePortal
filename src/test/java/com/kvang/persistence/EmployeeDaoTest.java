package com.kvang.persistence;

import com.kvang.entity.Employee;
import com.kvang.entity.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kvang on 9/21/17.
 */
public class EmployeeDaoTest {

    State state;
    StateDao stateDao;

    Employee employee;
    EmployeeDao employeeDao;

    int newEmployee = 0;
    int newState = 0;


    @Before
    public void setUp() throws Exception {

        stateDao = new StateDao();

        state = new State();
        state.setState_name("Test");
        state.setState_code("TE");

        stateDao.addState(state);

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
        employee.setState(stateDao.getStateById(newState).getState_code());


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


    }

    @Test
    public void addEmployee() throws Exception {
        newState = stateDao.addState(state);
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
        assertEquals("Employee state not returned correctly", employee.getState(), employeeDao.getEmployeeById(newEmployee).getState());

    }

    @Test
    public void deleteEmployee() throws Exception {
        employeeDao.addEmployee(employee);
        employeeDao.deleteEmployee(employee.getEmployeeId());
        assertNull("Employee not deleted", employeeDao.getEmployeeById(employee.getEmployeeId()));
    }

    @Test
    public void updateEmployee() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

}