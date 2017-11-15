package com.kvang.controller;

import com.kvang.persistence.ClientDao;
import com.kvang.persistence.EmployeeDao;
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
        urlPatterns = {"/assignClientToEmployee"}
)
public class assignClientToEmployee extends HttpServlet{

    private EmployeeDao employeeDao;
    private ClientDao clientDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("employee") != null) {
            session.setAttribute("employees", null);
        }

        if (session.getAttribute("client") != null) {
            session.setAttribute("clients", null);
        }

        employeeDao = new EmployeeDao();
        clientDao = new ClientDao();

        session.setAttribute("employees", employeeDao.getAllEmployees());
        session.setAttribute("clients", clientDao.getAllClients());

        String assignClientToEmployeeUrl = "AdminOnly/employeeAssignClientToEmployee.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(assignClientToEmployeeUrl);
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: finish and figure how to assign client to employee using many to many relationship
    }
}
