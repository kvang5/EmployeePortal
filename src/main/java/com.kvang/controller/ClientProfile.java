package com.kvang.controller;

import com.kvang.persistence.ClientDao;
import com.kvang.persistence.ClientNoteDao;
import lombok.extern.log4j.Log4j;
import org.hibernate.criterion.MatchMode;

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
        urlPatterns = {"/clientProfile"}
)
public class ClientProfile extends HttpServlet {

    private ClientDao clientDao;
    private ClientNoteDao clientNoteDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        String clientEmail = req.getParameter("email").toString();
        String clientIdString = req.getParameter("id").toString();

        int cId = Integer.parseInt(clientIdString);

        log.info(clientEmail);
        log.info(clientIdString);

        clientDao = new ClientDao();
        clientNoteDao = new ClientNoteDao();
        httpSession.setAttribute("clientProfile", clientDao.findByProperty("email", clientEmail, MatchMode.EXACT));
        httpSession.setAttribute("clientNotes", clientNoteDao.getClientNotesByClientId(cId));

        String clientProfileUrl = "/Employee/clientProfile.jsp";

        RequestDispatcher requestDispatcher1 = getServletContext().getRequestDispatcher(clientProfileUrl);
        requestDispatcher1.forward(req, resp);
    }
}
