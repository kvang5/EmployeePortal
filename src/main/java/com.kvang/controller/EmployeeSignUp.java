package com.kvang.controller;

import com.kvang.entity.Employee;
import com.kvang.entity.EmployeeRole;
import com.kvang.entity.State;
import com.kvang.entity.Title;
import com.kvang.persistence.*;
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

@Log4j
@WebServlet (
        urlPatterns = {"/employeeSignUp"}
)
public class EmployeeSignUp extends HttpServlet {

    private StateDao stateDao;
    private TitleDao titleDao;
    private EmployeeRoleDao employeeRoleDao;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("state") != null) {
            session.setAttribute("states", null);
        }

        if (session.getAttribute("title") != null) {
            session.setAttribute("titles", null);
        }

        if (session.getAttribute("employee") != null) {
            session.setAttribute("employees", null);
        }

        if (session.getAttribute("employeeRole") != null) {
            session.setAttribute("employeeRoles", null);
        }

        stateDao = new StateDao();
        titleDao = new TitleDao();
        employeeRoleDao = new EmployeeRoleDao();


        session.setAttribute("states", stateDao.getAllStates());
        session.setAttribute("titles", titleDao.getAllTitles());
        session.setAttribute("employeeRoles", employeeRoleDao.getAllEmployeeRoles());



        String employeeSignUpUrl = "AdminOnly/employeeSignUpForm.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(employeeSignUpUrl);
        requestDispatcher.forward(req, resp);
    }

    // This is employee signup form with employee information
        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();

        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String stateId = req.getParameter("state");
        String postal_zip_code = req.getParameter("postal_zip_code");
        String home_phone = req.getParameter("home_phone");
        String mobile_phone = req.getParameter("mobile_phone");
        String titleId = req.getParameter("title");
        String email = req.getParameter("email");
        String employeeRoleName = req.getParameter("employeeRoleName");

        //log.info("stateId: " + stateId);
        //log.info("titleId: " + titleId);

        // Parse String to Int for use of Id's
        int sId = Integer.parseInt(stateId);
        int tId = Integer.parseInt(titleId);
        //int erId = Integer.parseInt(employeeRoleId);

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            State state = (State) session.get(State.class, sId);
            Title title = (Title) session.get(Title.class, tId);
            //EmployeeRole empRole = (EmployeeRole) session.get(EmployeeRole.class, erId);
            Employee employee = new Employee();
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
            EmployeeRole employeeRole = new EmployeeRole();
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

        // When successful - redirect to servlet and prompts message
        String message = "New employee successfully added!";
        httpSession.setAttribute("message", message);
        resp.sendRedirect(req.getContextPath() + "/employeeSignUp");
    }
}
