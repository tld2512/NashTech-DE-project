package com.longdt.finalproject.controller;

import com.longdt.finalproject.log.MyLogger;
import com.longdt.finalproject.service.ConnectionService;
import com.longdt.finalproject.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "SignUpServlet", urlPatterns = "/sign-up")
public class SignUpServlet extends HttpServlet {
    private static final Logger logger = MyLogger.getLogger();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/sign-up.jsp");
        logger.info("Sign-up page is visited");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionService.getStoredConnection(req);
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        boolean hasError = false;
        String errorSignUpMessage = null;
        RequestDispatcher dispatcher = null;

        if (!checkSignUpInformation(connection, req)) {
            hasError = true;
            errorSignUpMessage = (String) req.getAttribute("errorSignUpMessage");
        }
        if (hasError) {
            req.setAttribute("errorSignUpMessage", errorSignUpMessage);
        } else {
            try {
                this.userService.createNewUser(connection, userName, password);
                errorSignUpMessage = "Your account has been created successfully";
                req.setAttribute("errorSignUpMessage", errorSignUpMessage);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("errorSignUpMessage", errorSignUpMessage);
        dispatcher = req.getRequestDispatcher("view/sign-up.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean checkSignUpInformation(Connection connection, HttpServletRequest req) {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String errorSignUpMessage = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            errorSignUpMessage = "User name and password can not null ";
            req.getSession().setAttribute("errorSignUpMessage", errorSignUpMessage);
            return false;
        }
        try {
            if (UserService.checkUserNameExisted(connection, userName)) {
                errorSignUpMessage = " User name has already taken";
                req.getSession().setAttribute("errorSignUpMessage", errorSignUpMessage);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!password.equals(password2)) {
            errorSignUpMessage = "Password and re-enter password must be matched";
            req.getSession().setAttribute("errorSignUpMessage", errorSignUpMessage);
            return false;
        }
        return true;
    }
}
