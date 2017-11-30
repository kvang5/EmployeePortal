package com.kvang.controller;

import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Auth servlet.
 *
 * TODO: finish all java doc for all methods
 */
@Log4j
@WebServlet(
        urlPatterns = {"/authServlet"}
)
@HttpConstraint
public class AuthServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //System.out.println("In the auth servlet");

        RequestDispatcher requestDispatcher;
        String adminUrl = "/AdminOnly/employeeSearch.jsp";
        String googleCalender = "/Employee/googleCalendar.jsp"; // test calendar
        String employeeUrl = "/Employee/clientSearch.jsp";
        String notAdminOrEmployeeUrl = "/loginError.jsp";
        if (req.isUserInRole("Administrator")) {
            requestDispatcher = getServletContext().getRequestDispatcher(googleCalender);
            requestDispatcher.forward(req, resp);
        } else if (req.isUserInRole("Registered-user")) {
            requestDispatcher = getServletContext().getRequestDispatcher(employeeUrl);
            requestDispatcher.forward(req, resp);
        } else {
            requestDispatcher = getServletContext().getRequestDispatcher(notAdminOrEmployeeUrl);
            requestDispatcher.forward(req, resp);
        }
    }

}
