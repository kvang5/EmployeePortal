package com.kvang.controller;

import com.kvang.persistence.EmployeeDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/employeeSearch"}
)
public class EmployeeSearch extends HttpServlet{

    //TODO Fix searching employees for admin
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        EmployeeDao employeeDao = new EmployeeDao();

        if (req.getParameter("submit").equals("search")) {
            req.setAttribute("employees", employeeDao.getEmployeeByFirstName(req.getParameter("searchTerm")));
        } else {
            req.setAttribute("employees", employeeDao.getAllEmployees());
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminOnly/employeeSearchResults.jsp");
        requestDispatcher.forward(req, resp);
    }
}
