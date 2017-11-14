package com.kvang.persistence;

import com.kvang.entity.Employee;
import com.kvang.entity.EmployeeRole;
import com.kvang.entity.State;
import com.kvang.entity.Title;
import lombok.extern.log4j.Log4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Log4j
public class EmployeeRoleDaoTest {

    StateDao stateDao;
    State state;

    TitleDao titleDao;
    Title title;

    EmployeeDao employeeDao;
    Employee employee;

    EmployeeRoleDao employeeRoleDao;
    EmployeeRole employeeRole;

    int newState;
    int newTitle;
    int newEmployee;
    int newEmployeeRole;


    @Before
    public void setUp() throws Exception {
        stateDao = new StateDao();

        state = new State();
        state.setState_code("SC");
        state.setState_name("South Carolina");


        titleDao = new TitleDao();
        title = new Title();
        title.setJobTitle("Testing job title");

        employeeDao = new EmployeeDao();
        employee = new Employee();
        employee.setFirst_name("TestF");
        employee.setLast_name("TestL");
        employee.setAddress1("123 Testing street");
        employee.setCity("Madison");
        employee.setPostal_zip_code("53590");
        employee.setEmail("kvang5@madisoncollege.edu");
        employee.setHome_phone("828-455-6682");
        employee.setMobile_phone("828-455-6682");
        employee.setState(state);
        employee.setTitle(title);
        employee.setPassword("test");

        employeeRoleDao = new EmployeeRoleDao();
        employeeRole = new EmployeeRole();
        employeeRole.setEmail(employee.getEmail());
        employeeRole.setRole_name("Testing");
        employeeRole.setEmployee(employee);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllEmployeeRoles() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);
        newEmployeeRole = employeeRoleDao.addEmployeeRole(employeeRole);
        List<EmployeeRole> employeeRoles = employeeRoleDao.getAllEmployeeRoles();
        assertTrue(employeeRoles.size() > 0);
    }

    @Test
    public void getEmployeeRoleById() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);
        newEmployeeRole = employeeRoleDao.addEmployeeRole(employeeRole);

        assertNotEquals("No employee role returned", 0, newEmployeeRole);
        assertEquals("Employee role Id not returned correctly", employeeRole.getEmployee_rolesId(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getEmployee_rolesId());
        assertEquals("Employee email not returned correctly", employeeRole.getEmail(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getEmail());
        assertEquals("Employee role name not returned correctly", employeeRole.getRole_name(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getRole_name());
        assertEquals("Employee id not returned correctly", employeeRole.getEmployee().getFirst_name(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getEmployee().getFirst_name());
    }

    @Test
    public void addEmployeeRole() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);
        newEmployeeRole = employeeRoleDao.addEmployeeRole(employeeRole);

        assertNotEquals("No new employee role added", 0, newEmployeeRole);
        assertEquals("Employee role Id not returned correctly", employeeRole.getEmployee_rolesId(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getEmployee_rolesId());
        assertEquals("Employee email not returned correctly", employeeRole.getEmail(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getEmail());
        assertEquals("Employee role name not returned correctly", employeeRole.getRole_name(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getRole_name());
        assertEquals("Employee id not returned correctly", employeeRole.getEmployee().getFirst_name(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getEmployee().getFirst_name());

    }

    @Test
    public void deleteEmployeeRole() throws Exception {
        stateDao.addState(state);
        titleDao.addTitle(title);
        employeeDao.addEmployee(employee);
        employeeRoleDao.addEmployeeRole(employeeRole);
        assertNotNull("Employee role is null", employeeRoleDao.getEmployeeRoleById(employeeRole.getEmployee_rolesId()));

        employeeRoleDao.deleteEmployeeRole(employeeRole.getEmployee_rolesId());

    }

    @Test
    public void updateEmployeeRole() throws Exception {
        newState = stateDao.addState(state);
        state.setStateId(stateDao.getStateById(1).getStateId());
        state.setState_code(stateDao.getStateById(1).getState_code());
        state.setState_name(stateDao.getStateById(1).getState_name());

        newTitle = titleDao.addTitle(title);
        title.setTitleId(titleDao.getTitleById(2).getTitleId());
        title.setJobTitle(titleDao.getTitleById(2).getJobTitle());

        newEmployee = employeeDao.addEmployee(employee);
        employee.setFirst_name(employeeDao.getEmployeeById(5).getFirst_name());
        employee.setLast_name(employeeDao.getEmployeeById(5).getLast_name());
        employee.setAddress1(employeeDao.getEmployeeById(5).getAddress1());
        employee.setAddress2(employeeDao.getEmployeeById(5).getAddress2());
        employee.setCity(employeeDao.getEmployeeById(5).getCity());
        employee.setPostal_zip_code(employeeDao.getEmployeeById(5).getPostal_zip_code());
        employee.setEmail(employeeDao.getEmployeeById(5).getEmail());
        employee.setHome_phone(employeeDao.getEmployeeById(5).getHome_phone());
        employee.setMobile_phone(employeeDao.getEmployeeById(5).getMobile_phone());
        employee.setState(employeeDao.getEmployeeById(5).getState());
        employee.setTitle(employeeDao.getEmployeeById(5).getTitle());
        employee.setPassword(employeeDao.getEmployeeById(5).getPassword());

        newEmployeeRole = employeeRoleDao.addEmployeeRole(employeeRole);
        employeeRole.setEmployee(employee);
        employeeRole.setEmail(employeeRole.getEmail());
        employeeRole.setRole_name("Testing2");

        employeeRoleDao.updateEmployeeRole(employeeRole);


        assertEquals("Employee role employee first name not updated", employeeRole.getEmployee().getFirst_name(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getEmployee().getFirst_name());
        assertEquals("Employee role email not updated", employeeRole.getEmail(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getEmail());
        assertEquals("Employee role name not updated", employeeRole.getRole_name(), employeeRoleDao.getEmployeeRoleById(newEmployeeRole).getRole_name());

    }

}