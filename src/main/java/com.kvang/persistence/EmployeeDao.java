package com.kvang.persistence;


import com.kvang.entity.*;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDao Class allows hibernate to assist in create(add), read(get), update, and delete (CRUD) for employees
 * <p>
 * Created by kvang on 9/21/17.
 */
@Log4j
public class EmployeeDao {

    /**
     * Gets all employees.
     *
     * @return the all employees
     */
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employees = session.createCriteria(Employee.class).list();
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

    /**
     * Gets employee by id.
     *
     * @param id the id
     * @return the employee by id
     */
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            employee = (Employee) session.get(Employee.class, id);
            Hibernate.initialize(employee.getState());
            Hibernate.initialize(employee.getTitle());
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


    /**
     * Add employee int.
     *
     * @param employee the employee
     * @return the int
     */
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

    /**
     * Delete employee.
     *
     * @param id the id
     */
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

    /**
     * Update employee.
     *
     * @param employee the employee
     */
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

    /**
     * Find by property list.
     *
     * @param propertyName the property name
     * @param value        the value
     * @param matchMode    the match mode
     * @return the list
     */
    public List<Employee> findByProperty(String propertyName, String value, MatchMode matchMode){
        Session session = null;
        List<Employee> items = new ArrayList<Employee>();
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            if (matchMode != null){
                items =  session.createCriteria(Employee.class).add(Restrictions.ilike(propertyName, value, matchMode)).list();
            }else{
                items =  session.createCriteria(Employee.class).add(Restrictions.ilike(propertyName, value, MatchMode.EXACT)).list();
            }
        } catch (HibernateException he) {
            log.error("Error getting Employee by propertyName", he);
        } catch (NullPointerException e) {
            log.error("Error getting Employee, employee don't exist: ", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return items;
    }

    /**
     * Check if employee exist in db boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean checkIfEmployeeExistInDB(String email) {
        boolean employeeExist = true;
        //String emailExistMessage = "";

        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Employee where email = :email");
            query.setParameter("email", email);
            query.uniqueResult();

            if (query.uniqueResult() == null) {
                // returns false if email DOES NOT EXIST
                log.info("Email does not exist in the DB.");
                employeeExist = false;
            } else {
                // returns true if email DOES EXIST
                //emailExistMessage = "This email is already in use, please select another one.."
                log.info("Email exists.");
                employeeExist = true;
            }

        } catch (HibernateException he) {
            log.error("Hibernate Exception error", he);
        } catch (Exception e) {
            log.error("Exception error", e);
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeExist;
    }

    //TODO: write test class for this method
    public void addNewEmployee(int sId, int tId, String first_name, String last_name, String address1, String address2,
                               String city, String postal_zip_code, String home_phone, String mobile_phone, String email,
                               boolean statusChecked, String employeeRoleName) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        State state;
        Title title;
        Employee employee;
        EmployeeRole employeeRole;

        try {
            tx = session.beginTransaction();
            state = (State) session.get(State.class, sId);
            title = (Title) session.get(Title.class, tId);
            employee = new Employee();
            employee.setFirst_name(first_name);
            employee.setLast_name(last_name);
            employee.setAddress1(address1);
            employee.setAddress2(address2);
            employee.setCity(city);
            employee.setState(state);
            employee.setPostal_zip_code(postal_zip_code);
            employee.setHome_phone(home_phone);
            employee.setMobile_phone(mobile_phone);
            employee.setTitle(title);
            employee.setEmail(email);
            employee.setPassword("GoldenSun1");
            employee.setStatus(statusChecked);
            employeeRole = new EmployeeRole();
            employeeRole.setEmail(email);
            employeeRole.setRole_name(employeeRoleName);
            employeeRole.setEmployee(employee);
            session.save(employee);
            session.save(employeeRole);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            log.info("Error saving employee with role: ", he);
        } catch (Exception e) {
            log.error("Employee was not added through sign up form: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    //TODO: write test for this method
    public void assignClientToEmployee(int empId, int clId) {
        Session session = null;
        Transaction tx = null;
        Employee employee;
        Client client;
        EmployeeDao employeeDao;
        ClientDao clientDao;

        try {

            session = SessionFactoryProvider.getSessionFactory().openSession();
            tx = session.beginTransaction();

            employeeDao = new EmployeeDao();
            clientDao = new ClientDao();

            employee = employeeDao.getEmployeeById(empId);
            client = clientDao.getClientById(clId);

            log.info(employee.getFirst_name());
            log.info(client.getFirst_name());

            employee.getClientSet().add(client);

            log.info(employee.getClientSet().size());

            session.saveOrUpdate(employee);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            log.error("Hibernate exception error: ", he);
        } catch (Exception e) {
            log.error("Exception error: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /*
    session = SessionFactoryProvider.getSessionFactory().openSession();
            tx = session.beginTransaction();

            employeeDao = new EmployeeDao();
            clientDao = new ClientDao();

            employee = employeeDao.getEmployeeById(empId);
            client = clientDao.getClientById(clId);

            employee.addClient(client);
            client.addEmployee(employee);

            session.saveOrUpdate(employee);
            tx.commit();
    */

    //TODO: write testing for this method
    //Maybe get by email or ID ????
    public List<Employee> getAllEmployeeByClient(String email) {
        List<Employee> employees = new ArrayList<Employee>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Query query = session.createQuery("select e from Employee e inner join e.clientSet c where e.email = :email");
            query.setParameter("email", email);
            employees = query.list();
            if (employees.isEmpty()) {
                log.info("employees list is empty");
                return null;
            } else {
                log.info("Inside the else");
                for (Employee emp : employees) {
                    log.info("inside the for loop");
                    log.info(emp.getEmployeeId() + " " + emp.getFirst_name());
                }
                log.info("outside the for loop");
                log.info("employees size : " + employees.size());
                return employees;
            }
        } catch (HibernateException he) {
            log.error("Error getting all employee by client", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employees;
    }

    //TODO: this does not work, not getting id
    public int getEmployeeId(String email) {
        List<Integer> employeeIdList = new ArrayList<Integer>();
        int empId = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Query query = session.createQuery("select e.employeeId from Employee e where e.email = :email");
            query.setParameter("email", email);
            employeeIdList = query.list();
        } catch (HibernateException he) {
            log.info("Error saving client: ", he);
        } catch (Exception e) {
            log.error("Client was not added through sign up form: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return empId;
    }

    /*public int getUserById(String email) {
        Session session = null;
        Employee employee = null;
        int eId = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            int = (Integer) session.load(Employee.class, email);
            Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }*/
}
