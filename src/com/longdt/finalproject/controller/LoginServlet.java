package com.longdt.finalproject.controller;

import com.longdt.finalproject.log.MyLogger;
import com.longdt.finalproject.model.User;
import com.longdt.finalproject.service.ConnectionService;
import com.longdt.finalproject.service.CookieService;
import com.longdt.finalproject.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final Logger logger = MyLogger.getLogger();

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/login.jsp");
        logger.info("Login page is visited");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String rememberMeStr = req.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        User user = null;
        boolean hasError = false;
        String errorMessage = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorMessage = "Require username and password";
            logger.warning("Username or password is not entered");
        } else {
            Connection connection = ConnectionService.getStoredConnection(req);
            try {
                user = UserService.findUser(connection, userName, password);
                if (user == null) {
                    hasError = true;
                    errorMessage = "Username or password is incorrect";
                    logger.warning("Incorrect username or password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorMessage = e.getMessage();
            }
        }
// if error occurred, forward to login page
        if (hasError) {
            user = new User();
            user.setUserName(userName);
            user.setPassword(password);

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/login.jsp");
            dispatcher.forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("name", user.getUserName());
            CookieService.storeLoggedInUser(session, user);
            if (remember) {
                CookieService.storeUserCookie(resp, user);
            } else {
                CookieService.deleteUserCookie(resp);
            }
            logger.info("User " + user.getUserName() + " logged in");
            resp.sendRedirect("/bookList");
        }

    }
}
