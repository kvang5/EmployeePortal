package com.kvang.persistence;


import com.kvang.entity.Employee;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDao Class allows hibernate to assist in create(add), read(get), update, and delete (CRUD) for employees
 *
 * Created by kvang on 9/21/17.
 */
@Log4j
public class EmployeeDao {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employees = session.createCriteria(Employee.class).list();
            log.info("employees: " + employees);
        } catch (HibernateException he) {
            log.error("Error getting all employees", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employees;
    }

    public Employee getEmployeeById(int id) {
        Employee employee = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employee = (Employee) session.get(Employee.class, id);
            Hibernate.initialize(employee.getState());
            Hibernate.initialize(employee.getTitle());
            //Hibernate.initialize();
        } catch (HibernateException he) {
            log.error("Error getting employee by id", he);
        } catch (Exception e) {
            log.error("General exception for getEmployeeById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employee;
    }


    public int addEmployee(Employee employee) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(employee);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error adding employee", he);
        } catch (Exception e) {
            log.error("General exception for addEmployee() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void deleteEmployee(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Employee employee = (Employee) session.load(Employee.class, id);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error deleting employee", he);
        } catch (Exception e) {
            log.error("General exception for deleteEmployee() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateEmployee(Employee employee) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error updating employee", he);
        } catch (Exception e) {
            log.error("General exception for updateEmployee() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Employee> getEmployeeByFirstName(String first_name) {
        List<Employee> employees = new ArrayList<Employee>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("first_name", first_name));
            employees = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting employee by last name", he);
        } catch (Exception e) {
            log.error("General exception for getEmployeeByLastName() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return employees;

    }

    /*public List<Employee> getListOfEmployees(String searchName) {
        List<Employee> employees = new ArrayList<Employee>();
        Session session;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            transaction = get
        }
    }*/

}
