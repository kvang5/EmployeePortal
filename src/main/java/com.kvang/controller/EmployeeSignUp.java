package com.kvang.controller;

import com.kvang.persistence.EmployeeDao;
import com.kvang.persistence.EmployeeRoleDao;
import com.kvang.persistence.StateDao;
import com.kvang.persistence.TitleDao;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Employee sign up.
 */
@Log4j
@WebServlet (
        urlPatterns = {"/employeeSignUp"}
)
public class EmployeeSignUp extends HttpServlet {

    private StateDao stateDao;
    private TitleDao titleDao;
    private EmployeeRoleDao employeeRoleDao;
    private EmployeeDao employeeDao;
    private Boolean statusChecked = false;
    private Boolean emailExist = false;


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
        session.setAttribute("employeeRoles", employeeRoleDao.getLimitEmployeeRoles());

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

        if (req.getParameter("status") == null) {
            statusChecked = false;
        } else {
            statusChecked = true;
        }


        // Parse String to Int for use of Id's
        int sId = Integer.parseInt(stateId);
        int tId = Integer.parseInt(titleId);

        employeeDao = new EmployeeDao();
        //emailExist = employeeDao.checkIfEmployeeExistInDB(email);
        //log.info(emailExist);
        //if (emailExist == false) {
            employeeDao.addNewEmployee(sId, tId, first_name, last_name, address1, address2, city,
                    postal_zip_code, home_phone, mobile_phone, email, statusChecked, employeeRoleName);
        //} else {
            //log.info("Email exist!!!");
            //log.info("Did not add to Database!!!");
        //}


        // This is now handled by validation rules
        // When successful - redirect to servlet and prompts message
        /*String message = "New employee successfully added!";
        httpSession.setAttribute("message", message);*/
        //resp.sendRedirect(req.getContextPath() + "/employeeSignUp");
    }
}
