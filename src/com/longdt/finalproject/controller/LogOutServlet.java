package com.longdt.finalproject.controller;

import com.longdt.finalproject.log.MyLogger;
import com.longdt.finalproject.model.User;
import com.longdt.finalproject.service.ConnectionService;
import com.longdt.finalproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class LogOutServlet extends HttpServlet {
    private static final Logger logger = MyLogger.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = ConnectionService.getStoredConnection(request);
        HttpSession session = request.getSession();
        try {
            User user = UserService.findUser(conn, (String) session.getAttribute("name"));
            if (user == null) {
                request.getRequestDispatcher("/view/login.jsp").include(request, response);
            }
            if (user != null) {
                logger.info("User " + user.getUserName() + " is logged out");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/view/login.jsp").include(request, response);
        session.invalidate();
    }
}
