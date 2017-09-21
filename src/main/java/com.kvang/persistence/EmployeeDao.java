package com.kvang.persistence;


import com.kvang.entity.Employee;

import java.util.List;

/**
 * Interface EmployeeDao for implementation to CRUD employees
 *
 * Created by kvang on 9/21/17.
 */
public interface EmployeeDao {

    public List<Employee> getAllEmployees();
    public Employee getEmployee(int id);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);

}
