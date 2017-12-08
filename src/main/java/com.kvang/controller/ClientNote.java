package com.kvang.controller;

import com.kvang.entity.Client;
import com.kvang.entity.Employee;
import com.kvang.persistence.ClientDao;
import com.kvang.persistence.ClientNoteDao;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Client note.
 */
@Log4j
@WebServlet (
        urlPatterns = {"/clientNote"}
)
public class ClientNote extends HttpServlet {

    private Client client;
    private ClientDao clientDao;
    private Employee employee;
    private EmployeeDao employeeDao;
    private ClientNote clientNote;
    private ClientNoteDao clientNoteDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("client") != null) {
            session.setAttribute("clients", null);
        }

        clientDao = new ClientDao();

        String userEmail = req.getRemoteUser();

        session.setAttribute("clients", clientDao.getAllClientByEmployee(userEmail));

        String clientNoteUrl = "Employee/employeeClientNote.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(clientNoteUrl);
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        String clientId = req.getParameter("client");
        String careDate = req.getParameter("date");
        String careTime = req.getParameter("care_time");
        String desc = req.getParameter("description");
        String comments = req.getParameter("comments");

        int cId = Integer.parseInt(clientId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(careDate, formatter);
        Double time = Double.parseDouble(careTime);


        String empEmail = req.getRemoteUser();

        clientNoteDao = new ClientNoteDao();
        clientNoteDao.addClientNoteFromEmployee(cId, date, time, desc, comments, empEmail);
    }
}
