package com.kvang.controller;

import com.kvang.entity.Client;
import com.kvang.entity.Employee;
import com.kvang.persistence.ClientDao;
import com.kvang.persistence.EmployeeDao;
import com.kvang.persistence.SessionFactoryProvider;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Assign client to employee.
 */
@Log4j
@WebServlet (
        urlPatterns = {"/assignClientToEmployee"}
)
public class AssignClientToEmployee extends HttpServlet{

    private EmployeeDao employeeDao;
    private ClientDao clientDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("employee") != null) {
            session.setAttribute("employees", null);
        }

        if (session.getAttribute("client") != null) {
            session.setAttribute("clients", null);
        }

        employeeDao = new EmployeeDao();
        clientDao = new ClientDao();

        session.setAttribute("employees", employeeDao.getAllEmployees());
        session.setAttribute("clients", clientDao.getAllClients());

        String assignClientToEmployeeUrl = "AdminOnly/employeeAssignClientToEmployee.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(assignClientToEmployeeUrl);
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        String employeeId = req.getParameter("employee");
        String clientId = req.getParameter("client");

        int empId = Integer.parseInt(employeeId);
        int clId = Integer.parseInt(clientId);

        Session session = null;
        Transaction tx;
        Employee employee;
        Client client;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            tx = session.beginTransaction();

            employee = employeeDao.getEmployeeById(empId);
            client = clientDao.getClientById(clId);

            employee.addClient(client);
            client.addEmployee(employee);

            session.saveOrUpdate(employee);
            tx.commit();
        } catch (HibernateException he) {
            log.error("Hibernate exception error: ", he);
        } catch (Exception e) {
            log.error("Exception error: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
