package com.kvang.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (
        urlPatterns = {"/nameGenerator"}
)
public class NameGenerator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: This method will get the users email, with getUserPrincipal http method.
        // use this for api calendar outlook app
    }
}
