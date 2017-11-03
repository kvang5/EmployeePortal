package com.kvang.controller;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/authSevlet"}
)
public class AuthServlet extends HttpServlet{

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("This is the Test Servlet");

        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            out.print("<br/>Header Name: <em>" + headerName);
            String headerValue = req.getHeader(headerName);
            out.print("</em>, Header Value: <em>" + headerValue);
            out.println("</em>");
        }

        out.println("<br/>");
        out.println("-----------------------");
        out.println("<br/>");

        out.println("req.getContextPath(): " + req.getContextPath() + "<br/>");
        out.println("req.getRemoteUser(): " + req.getRemoteUser() + "<br/>");
        out.println("req.getUserPrincipal(): " + req.getUserPrincipal() + "<br/>");
        out.println("req.getUserPrincipal().getName(): " + req.getUserPrincipal().getName() + "<br/>");
        out.println("req.getUserPrincipal().toString(): " + req.getUserPrincipal().toString() + "<br/>");
        out.println("req.isUserInRole: " + req.isUserInRole("administrator") + "<br/>");
        out.println("req.getAuthType(): " + req.getAuthType() + "<br/>");
        out.println("req.getQueryString(): " + req.getQueryString() + "<br/>");
        out.println("req.getServletPath(): " + req.getServletPath() + "<br/>");
        */

        RequestDispatcher requestDispatcher;
        String adminUrl = "/AdminOnly/employeeSearch.jsp";
        String employeeUrl = "/Employee/employeeDashboard.jsp";
        String notAdminOrEmployeeUrl = "/loginError.jsp";
        if (req.isUserInRole("Administrator")) {
            requestDispatcher = getServletContext().getRequestDispatcher(adminUrl);
            requestDispatcher.forward(req, resp);
        } else if (req.isUserInRole("Registered-user")) {
            requestDispatcher = getServletContext().getRequestDispatcher(employeeUrl);
            requestDispatcher.forward(req, resp);
        } else {
            requestDispatcher = getServletContext().getRequestDispatcher(notAdminOrEmployeeUrl);
            requestDispatcher.forward(req, resp);
        }
    }

}
