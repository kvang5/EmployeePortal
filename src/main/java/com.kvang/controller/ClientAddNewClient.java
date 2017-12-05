package com.kvang.controller;

import com.kvang.entity.Client;
import com.kvang.entity.State;
import com.kvang.persistence.SessionFactoryProvider;
import com.kvang.persistence.StateDao;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

        log.info("sId: " + sId);

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            State state = (State) session.get(State.class, sId);
            Client client = new Client();
            client.setFirst_name(first_name);
            client.setLast_name(last_name);
            client.setAddress1(address1);
            client.setAddress2(address2);
            client.setCity(city);
            client.setState(state);
            client.setPostal_zip_code(postal_zip_code);
            client.setEmail(email);
            client.setHome_phone(home_phone);
            client.setMobile_phone(mobile_phone);
            client.setStatus(statusChecked);
            session.save(client);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            log.info("Error saving client: ", he);
        } catch (Exception e) {
            log.error("Client was not added through sign up form: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // When successful - redirect to servlet and prompts message
        /*String message = "New client successfully added!";
        httpSession.setAttribute("message", message);
        resp.sendRedirect(req.getContextPath() + "/addNewClient");*/
    }
}
