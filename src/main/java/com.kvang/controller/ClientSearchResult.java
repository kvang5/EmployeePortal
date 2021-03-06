package com.kvang.controller;

import com.kvang.persistence.ClientDao;
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
        urlPatterns = {"/clientSearchResult"}
)
public class ClientSearchResult extends HttpServlet {

    private ClientDao clientDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        clientDao = new ClientDao();

        if (req.getParameter("submit").equals("search")) {
            req.setAttribute("clients", clientDao.findByProperty("last_name", req.getParameter("searchTerm"), MatchMode.ANYWHERE));
        } else {
            req.setAttribute("clients", clientDao.getAllClients());
        }
        RequestDispatcher requestDispatcher2 = req.getRequestDispatcher("Employee/clientSearchResults.jsp");
        requestDispatcher2.forward(req, resp);
    }
}
