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

public class ProfileServlet extends HttpServlet {
    private static final Logger logger = MyLogger.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "changePassword": {
                try {
                    goToChangePassword(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {
                goToProfile(request, response);
                logger.info("profile page has accessed");
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "changePassword": {
                try {
                    changePassword(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {
                goToProfile(request, response);
                break;
            }
        }
    }

    private void goToChangePassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Connection connection = ConnectionService.getStoredConnection(request);
        HttpSession session = request.getSession();
        if (session != null) {
            String userName = (String) session.getAttribute("name");
            User user = UserService.findUser(connection, userName);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/view/changePassword.jsp").forward(request, response);
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Connection connection = ConnectionService.getStoredConnection(request);
        HttpSession session = request.getSession();
        String message = "";
        if (session != null) {
            String userName = (String) session.getAttribute("name");
            User user = UserService.findUser(connection, userName);
            String oldPw = request.getParameter("oldPw");
            String newPw = request.getParameter("newPw");
            String newPw2 = request.getParameter("newPw2");
            if (UserService.checkPassword(connection, user, oldPw)) {
                if (newPw.equals(newPw2) && !newPw.equals("")) {
                    UserService.updatePassword(connection, user, newPw);
                    message = "Password changed successfully";
                    logger.info("User: " + userName + " changed the password");
                } else if (newPw.equals("")) {
                    message = "Please enter a valid password";
                } else {
                    message = "Re-entered password is not matched";
                }
            } else {
                message = "Old password is incorrect";
            }
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/view/changePassword.jsp").forward(request, response);
    }

    private void goToProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionService.getStoredConnection(request);
        HttpSession session = request.getSession(false);
        if (session != null) {
            try {
                String name = (String) session.getAttribute("name");
                User user = UserService.findUser(connection, name);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/view/profile.jsp").forward(request, response);
            } catch (SQLException | ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("/view/login.jsp").include(request, response);
        }
    }
}