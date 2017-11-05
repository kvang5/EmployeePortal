package com.kvang.controller;

import com.kvang.persistence.EmployeeDao;
import lombok.extern.log4j.Log4j;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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
