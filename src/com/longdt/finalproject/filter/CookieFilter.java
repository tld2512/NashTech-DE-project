package com.longdt.finalproject.filter;


import com.longdt.finalproject.model.User;
import com.longdt.finalproject.service.ConnectionService;
import com.longdt.finalproject.service.CookieService;
import com.longdt.finalproject.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {
    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        User userInSession = CookieService.getLoggedInUser(session);
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            filterChain.doFilter(request, response);
            return;
        }

        Connection conn = ConnectionService.getStoredConnection(request);
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null & conn == null) {
            String userName = CookieService.getUserNameInCookie(req);
            try {
                User user = UserService.findUser(conn, userName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
        filterChain.doFilter(request, response);
    }


}
