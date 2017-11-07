package com.kvang.persistence;


import com.kvang.entity.Employee;
import com.kvang.entity.State;
import com.kvang.entity.Title;
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

    // TODO Need to add password... have email send temporary password to employee email.
    public int addEmployeeFromSignUp(String first_name, String last_name, String address1, String address2, String city,
                                     State stateId, String postal_zip_code, String home_phone, String mobile_phone, Title titleId,
                                     String email) {
        StateDao stateDao = new StateDao();
        TitleDao titleDao = new TitleDao();
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Employee employee = new Employee();
            employee.setFirst_name(first_name);
            employee.setLast_name(last_name);
            employee.setAddress1(address1);
            employee.setAddress2(address2);
            employee.setCity(city);
            employee.setState(stateId);
            employee.setPostal_zip_code(postal_zip_code);
            employee.setHome_phone(home_phone);
            employee.setMobile_phone(mobile_phone);
            employee.setTitle(titleId);
            employee.setEmail(email);
            employee.setPassword("GoldenSun1");
            id = (int) session.save(employee);
            transaction.commit();
            log.info(transaction);
        } catch (HibernateException he) {
            log.error("Error signing up employee from addEmployeeFromSignUp(): ", he);
        } catch (Exception e) {
            log.error("General exception for addEmployeeFromSignUp() is caught: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

}
