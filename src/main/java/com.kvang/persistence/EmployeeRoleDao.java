package com.kvang.persistence;

import com.kvang.entity.EmployeeRole;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Employee role dao.
 */
@Log4j
public class EmployeeRoleDao {

    /**
     * Gets all employee roles.
     *
     * @return the all employee roles
     */
    public List<EmployeeRole> getAllEmployeeRoles() {
        List<EmployeeRole> employeeRoles = new ArrayList<EmployeeRole>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employeeRoles = session.createCriteria(EmployeeRole.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all employeeRoless", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeRoles;
    }

    /**
     * Gets limit employee roles.
     *
     * @return the limit employee roles
     */
    public List<EmployeeRole> getLimitEmployeeRoles() {
        List<EmployeeRole> employeeRoles = new ArrayList<EmployeeRole>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(EmployeeRole.class);
            criteria.setMaxResults(2);
            employeeRoles = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting all employeeRoless", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeRoles;
    }

    /**
     * Gets employee role by id.
     *
     * @param id the id
     * @return the employee role by id
     */
    public EmployeeRole getEmployeeRoleById(int id) {
        EmployeeRole employeeRole = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employeeRole = (EmployeeRole) session.get(EmployeeRole.class, id);
            Hibernate.initialize(employeeRole.getEmployee());
        } catch (HibernateException he) {
            log.error("Error getting employeeRoles by id", he);
        } catch (Exception e) {
            log.error("General exception for getEmployeeRolesById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeRole;
    }


    /**
     * Add employee role int.
     *
     * @param employeeRole the employee role
     * @return the int
     */
    public int addEmployeeRole(EmployeeRole employeeRole) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(employeeRole);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error adding employeeRoles", he);
        } catch (Exception e) {
            log.error("General exception for addEmployeeRoles() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    /**
     * Delete employee role.
     *
     * @param id the id
     */
    public void deleteEmployeeRole(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            EmployeeRole employeeRole = (EmployeeRole) session.load(EmployeeRole.class, id);
            session.delete(employeeRole);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error deleting employeeRoles", he);
        } catch (Exception e) {
            log.error("General exception for deleteEmployeeRoles() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Update employee role.
     *
     * @param employeeRole the employee role
     */
    public void updateEmployeeRole(EmployeeRole employeeRole) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employeeRole);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error updating employeeRoles", he);
        } catch (Exception e) {
            log.error("General exception for updateEmployeeRoles() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
