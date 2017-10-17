package com.kvang.persistence;

import com.kvang.entity.EmployeeRole;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRoleDao {

    private final Logger logger = Logger.getLogger(this.getClass());

    public List<EmployeeRole> getAllEmployeeRoles() {
        List<EmployeeRole> employeeRoles = new ArrayList<EmployeeRole>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employeeRoles = session.createCriteria(EmployeeRole.class).list();
        } catch (HibernateException he) {
            logger.error("Error getting all employeeRoless", he);
        } catch (Exception e) {
            logger.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeRoles;
    }

    public EmployeeRole getEmployeeRoleById(int id) {
        EmployeeRole employeeRole = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employeeRole = (EmployeeRole) session.get(EmployeeRole.class, id);
            Hibernate.initialize(employeeRole.getEmployee());
        } catch (HibernateException he) {
            logger.error("Error getting employeeRoles by id", he);
        } catch (Exception e) {
            logger.error("General exception for getEmployeeRolesById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeRole;
    }


    public int addEmployeeRole(EmployeeRole employeeRole) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(employeeRole);
            transaction.commit();
        } catch (HibernateException he) {
            logger.error("Error adding employeeRoles", he);
        } catch (Exception e) {
            logger.error("General exception for addEmployeeRoles() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void deleteEmployeeRole(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            EmployeeRole employeeRole = (EmployeeRole) session.load(EmployeeRole.class, id);
            session.delete(employeeRole);
            transaction.commit();
        } catch (HibernateException he) {
            logger.error("Error deleting employeeRoles", he);
        } catch (Exception e) {
            logger.error("General exception for deleteEmployeeRoles() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateEmployeeRole(EmployeeRole employeeRole) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employeeRole);
            transaction.commit();
        } catch (HibernateException he) {
            logger.error("Error updating employeeRoles", he);
        } catch (Exception e) {
            logger.error("General exception for updateEmployeeRoles() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
