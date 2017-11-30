package com.kvang.controller;

import com.kvang.persistence.EmployeeDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Employee search.
 */
@WebServlet(
        urlPatterns = {"/employeeSearch"}
)
public class EmployeeSearch extends HttpServlet{

    /**
     * The Employee dao.
     */
    EmployeeDao employeeDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String adminUrl = "/AdminOnly/employeeSearch.jsp";

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(adminUrl);
        requestDispatcher.forward(req, resp);
    }
}
