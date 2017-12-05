package com.kvang.controller;

import com.kvang.persistence.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (
        urlPatterns = {"/checkEmail"}
)
public class CheckEmail extends HttpServlet {

    private EmployeeDao employeeDao;

    /*TODO: make ajax call to check email exist in DB for error message*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        String email = req.getParameter("email");

        employeeDao.checkIfEmployeeExistInDB(email);

    }
}
