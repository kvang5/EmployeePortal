package com.kvang.persistence;


import com.kvang.entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDao Class allows hibernate to assist in create(add), read(get), update, and delete (CRUD) for employees
 *
 * Created by kvang on 9/21/17.
 */
public class EmployeeDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employees = session.createCriteria(Employee.class).list();
        } catch (HibernateException he) {
            logger.error("Error getting all employees", he);
        } catch (Exception e) {
            logger.error("General exception is caught", e);
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
        } catch (HibernateException he) {
            logger.error("Error getting employee by id", he);
        } catch (Exception e) {
            logger.error("General exception for getEmployeeById() is caught", e);
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
            logger.error("Error adding employee", he);
        } catch (Exception e) {
            logger.error("General exception for addEmployee() is caught", e);
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
            logger.error("Error deleting employee", he);
        } catch (Exception e) {
            logger.error("General exception for deleteEmployee() is caught", e);
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
            logger.error("Error updating employee", he);
        } catch (Exception e) {
            logger.error("General exception for updateEmployee() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
