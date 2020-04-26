package com.longdt.finalproject.service;

import com.longdt.finalproject.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CookieService {
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    public static void storeLoggedInUser(HttpSession session, User loggedInUser) {
        session.setAttribute("loggedInUser", loggedInUser);
    }

    public static User getLoggedInUser(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        return loggedInUser;
    }

    public static void storeUserCookie(HttpServletResponse response, User user){
        Cookie cookie = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
    }

    public static String getUserNameInCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie: cookies){
                if (ATT_NAME_USER_NAME.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response){
        Cookie cookie = new Cookie(ATT_NAME_USER_NAME, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
