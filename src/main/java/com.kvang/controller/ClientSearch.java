package com.kvang.controller;

import com.kvang.persistence.ClientDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Client search.
 */
@WebServlet (
        urlPatterns = {"/clientSearch"}
)
public class ClientSearch extends HttpServlet {
    /**
     * The Client dao.
     */
    ClientDao clientDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String clientSearchUrl = "Employee/clientSearch.jsp";

        RequestDispatcher requestDispatcher1 = req.getRequestDispatcher(clientSearchUrl);
        requestDispatcher1.forward(req, resp);
    }
}
