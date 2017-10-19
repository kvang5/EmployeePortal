package com.kvang.controller;

import com.kvang.entity.EmployeeRole;

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
        EmployeeRole employeeRole = new EmployeeRole();
        if (req.getParameter("login-btn").equals("login")) {
            if (employeeRole.getRole_name().equals("administrator")) {
                resp.sendRedirect(req.getContextPath() + "/AdminOnly/employeeSearch.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/loginError.jsp");
            }
        }
    }
}
