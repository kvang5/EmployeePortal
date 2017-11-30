package com.kvang.controller;

import com.kvang.persistence.EmployeeDao;
import org.hibernate.criterion.MatchMode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (
        urlPatterns = {"/employeeSearchResult"}
)
public class EmployeeSearchResult extends HttpServlet {

    /**
     * The Employee dao.
     */
    EmployeeDao employeeDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        employeeDao = new EmployeeDao();

        if (req.getParameter("submit").equals("search")) {
            req.setAttribute("employees", employeeDao.findByProperty("last_name", req.getParameter("searchTerm"), MatchMode.ANYWHERE));
        } else {
            req.setAttribute("employees", employeeDao.getAllEmployees());
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminOnly/employeeSearchResults.jsp");
        requestDispatcher.forward(req, resp);
    }

}
