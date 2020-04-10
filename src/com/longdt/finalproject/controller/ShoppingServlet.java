package com.longdt.finalproject.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookServlet", urlPatterns = "/bookList")
public class ShoppingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String activity = req.getParameter("activity");
        if (activity.equals("addToCart")){
            addBookToCart(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String activity = req.getParameter("activity");
        if (activity.equals("addToCart")){
            addBookToCart(req,resp);
        }
    }

    private void addBookToCart(HttpServletRequest req, HttpServletResponse resp) {
    }
}
