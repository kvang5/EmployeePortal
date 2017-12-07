package com.kvang.controller;

import com.kvang.persistence.ClientDao;
import com.kvang.persistence.StateDao;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Client add new client.
 */
@Log4j
@WebServlet (
        urlPatterns = {"/addNewClient"}
)
public class ClientAddNewClient extends HttpServlet {

    private StateDao stateDao;
    private ClientDao clientDao;
    private Boolean statusChecked = false;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("state") != null) {
            session.setAttribute("states", null);
        }

        stateDao = new StateDao();

        session.setAttribute("states", stateDao.getAllStates());

        String addNewClientUrl = "AdminOnly/clientAddNewClientForm.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(addNewClientUrl);
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String stateId = req.getParameter("state");
        String postal_zip_code = req.getParameter("postal_zip_code");
        String home_phone = req.getParameter("home_phone");
        String mobile_phone = req.getParameter("mobile_phone");
        String email = req.getParameter("email");

        if (req.getParameter("status") == null) {
            statusChecked = false;
        } else {
            statusChecked = true;
        }

        // Parse String to Int for use of Id's
        int sId = Integer.parseInt(stateId);

        clientDao = new ClientDao();

        clientDao.addNewClient(first_name, last_name, address1, address2, city, sId, postal_zip_code,
                email, home_phone, mobile_phone, statusChecked);

        // When successful - redirect to servlet and prompts message
        /*String message = "New client successfully added!";
        httpSession.setAttribute("message", message);
        resp.sendRedirect(req.getContextPath() + "/addNewClient");*/
    }
}
