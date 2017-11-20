package com.kvang.persistence;

import com.kvang.entity.Employee;
import com.kvang.entity.State;
import com.kvang.entity.Title;
import lombok.extern.log4j.Log4j;
import org.hibernate.criterion.MatchMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kvang on 9/21/17.
 */

@Log4j
public class EmployeeDaoTest {

    StateDao stateDao;
    State state;

    TitleDao titleDao;
    Title title;

    EmployeeDao employeeDao;
    Employee employee;

    int newState = 0;
    int newTitle = 0;
    int newEmployee = 0;

    @Before
    public void setUp() throws Exception {
        stateDao = new StateDao();

        state = new State();
        state.setState_code("WI");
        state.setState_name("Wisconsin");

        titleDao = new TitleDao();

        title = new Title();
        title.setJobTitle("Registered Nurse");

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
        employee.setTitle(title);
        employee.setPassword("test");
        employee.setStatus(true);

    }


    @After
    public void tearDown() throws Exception {
        /*if (newState != 0) {
            stateDao.deleteState(newState);
        }

        if (newTitle != 0) {
            titleDao.deleteTitle(newTitle);
        }

        if (newEmployee != 0) {
            employeeDao.deleteEmployee(newEmployee);
        }*/


    }

    @Test
    public void getAllEmployees() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);
        List<Employee> employees = employeeDao.getAllEmployees();
        assertTrue(employees.size() > 0);
    }

    @Test
    public void getEmployeeById() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
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
        assertEquals("Employee job title not returned correctly", employee.getTitle().getJobTitle(), employeeDao.getEmployeeById(newEmployee).getTitle().getJobTitle());
        assertEquals("Employee password not returned correctly", employee.getPassword(), employeeDao.getEmployeeById(newEmployee).getPassword());
        assertEquals("Employee status not returned correctly", employee.getStatus(), employeeDao.getEmployeeById(newEmployee).getStatus());
    }

    @Test
    public void addEmployee() throws Exception {
        newState = stateDao.addState(state);
        log.info("newState: " + newState);
        newTitle = titleDao.addTitle(title);
        log.info("newTitle: " + newTitle);
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
        assertEquals("Employee job title not returned correctly", employee.getTitle().getJobTitle(), employeeDao.getEmployeeById(newEmployee).getTitle().getJobTitle());
        assertEquals("Employee password not returned correctly", employee.getPassword(), employeeDao.getEmployeeById(newEmployee).getPassword());
        assertEquals("Employee status not returned correctly", employee.getStatus(), employeeDao.getEmployeeById(newEmployee).getStatus());

    }

    @Test
    public void deleteEmployee() throws Exception {
        stateDao.addState(state);
        titleDao.addTitle(title);
        employeeDao.addEmployee(employee);
        assertNotNull("Employee is null", employeeDao.getEmployeeById(employee.getEmployeeId()));

        employeeDao.deleteEmployee(employee.getEmployeeId());
    }

    @Test
    public void updateEmployee() throws Exception {
        newState = stateDao.addState(state);
        state.setStateId(stateDao.getStateById(6).getStateId());
        state.setState_code(stateDao.getStateById(6).getState_code());
        state.setState_name(stateDao.getStateById(6).getState_name());

        newTitle = titleDao.addTitle(title);
        title.setTitleId(titleDao.getTitleById(3).getTitleId());
        title.setJobTitle(titleDao.getTitleById(3).getJobTitle());

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
        employee.setTitle(title);
        employee.setPassword("test2");

        employeeDao.updateEmployee(employee);

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
        assertEquals("Employee job title not updated", employee.getTitle().getJobTitle(), employeeDao.getEmployeeById(newEmployee).getTitle().getJobTitle());
        assertEquals("Employee password not returned correctly", employee.getPassword(), employeeDao.getEmployeeById(newEmployee).getPassword());
        assertEquals("Employee status not returned correctly", employee.getStatus(), employeeDao.getEmployeeById(newEmployee).getStatus());

    }

    @Test
    public void getEmployeeByFirstName() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);

        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        assertEquals("Kyle", employee.getFirst_name());
        assertEquals(1, employees.size());
        log.info("employees.size(): " + employees.size());
        log.info("employees: " + employees.toString());

    }

    @Test
    public void findByProperty() throws Exception {
        List<Employee> employees1 = employeeDao.findByProperty("first_name", "k", MatchMode.ANYWHERE);
        assertTrue(employees1.size() > 0);

        List<Employee> employees2 = employeeDao.findByProperty("first_name", "Kyle", MatchMode.EXACT);
        assertTrue(employees2.size() > 0);
    }
}