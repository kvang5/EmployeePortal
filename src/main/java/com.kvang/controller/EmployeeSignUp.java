package com.kvang.controller;

import com.kvang.entity.State;
import com.kvang.entity.Title;
import com.kvang.persistence.EmployeeDao;
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

@Log4j
@WebServlet (
        urlPatterns = {"/employeeSignUp"}
)
public class EmployeeSignUp extends HttpServlet {

    private State state;
    private StateDao stateDao;
    private Title title;
    private TitleDao titleDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("state") != null) {
            session.setAttribute("state", null);
        }

        stateDao = new StateDao();

        session.setAttribute("state", stateDao.getAllStates());
        log.info(session);

        String employeeSignUpUrl = "AdminOnly/employeeSignUpForm.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(employeeSignUpUrl);
        dispatcher.forward(req, resp);
    }

    // This is employee signup form with employee information
        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String postal_zip_code = req.getParameter("postal_zip_code");
        String home_phone = req.getParameter("home_phone");
        String mobile_phone = req.getParameter("mobile_phone");
        String title = req.getParameter("title");
        String email = req.getParameter("email");

        try {
            EmployeeDao employeeDao = new EmployeeDao();
            //employeeDao.addEmployeeFromSignUp(first_name, last_name, address1, address2, city, state, postal_zip_code, home_phone, mobile_phone, title, email);
            //log.info("add employee from sign up working: " +  employeeDao.addEmployeeFromSignUp(first_name, last_name, address1, address2, city, State state, postal_zip_code, home_phone, mobile_phone, title, email););
        } catch (Exception e) {
            log.error("Employee was not added through sign up form: ", e);
        }

    }
}
