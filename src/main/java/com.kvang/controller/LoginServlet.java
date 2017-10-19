package com.kvang.controller;

import com.kvang.entity.EmployeeRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //EmployeeDao employeeDao = new EmployeeDao();
        EmployeeRole employeeRole = new EmployeeRole();
        RequestDispatcher requestDispatcher;

        if (req.getParameter("login").equals("login")) {
            if (employeeRole.getRole_name().equals("administrator")) {
                resp.sendRedirect("../AdminOnly/employeeSearch.jsp");
            } else {
                requestDispatcher = req.getRequestDispatcher("basicDashboard.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
