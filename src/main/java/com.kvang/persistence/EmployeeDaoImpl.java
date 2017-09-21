package com.kvang.persistence;

import com.kvang.entity.Employee;
import com.kvang.entity.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvang on 9/21/17.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    List<Employee> employees;

    public EmployeeDaoImpl() {
        employees = new ArrayList<Employee>();
        State state = null;

        Employee employee = new Employee();
        employee.setFirst_name("Kyle");
        employee.setLast_name("Vang");
        employee.setAddress1("123 Some Street");
        employee.setCity("Madison");
        employee.setPostal_zip_code("12345");
        employee.setEmail("kvang5@madisoncollege.edu");
        employee.setHome_phone("123-123-1234");
        employee.setMobile_phone(employee.getHome_phone());
        employee.setState(state.getState_code());
        employees.add(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employees;
    }

    @Override
    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        //employees.get(employee.getEmployeeId()).set

    }

    @Override
    public void deleteEmployee(Employee employee) {
        employees.remove(employee.getEmployeeId());

    }
}
