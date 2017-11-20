package com.kvang.controller;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/authSevlet"}
)
public class AuthServlet extends HttpServlet{

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        String adminUrl = "/AdminOnly/employeeSearch.jsp";
        String employeeUrl = "/Employee/clientSearch.jsp";
        String notAdminOrEmployeeUrl = "/loginError.jsp";
        if (req.isUserInRole("Administrator")) {
            requestDispatcher = getServletContext().getRequestDispatcher(adminUrl);
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
