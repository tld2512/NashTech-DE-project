package com.longdt.finalproject.service;

import com.longdt.finalproject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public static User findUser(Connection conn, String userName, String password) throws SQLException {

        String sql = "select a.user_name, a.password from user a " + " where a.user_name = ? and a.password= ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        }
        return null;
    }

    public static User findUser(Connection conn, String userName) throws SQLException {

        String sql = "select a.user_name, a.password from user a " + " where a.user_name = ? ";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, userName);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String password = rs.getString("password");
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        }
        rs.close();
        ps.close();
        return null;
    }

    public static void updatePassword(Connection connection, User user, String newPw) throws SQLException {
        String sql = "UPDATE user t SET t.password = ? WHERE user_name LIKE ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, newPw);
        ps.setString(2, user.getUserName());
        ps.executeUpdate();
        ps.close();
    }

    public static boolean checkPassword(Connection connection, User user, String inputPassword) throws SQLException {
        String sql = "SELECT password from user WHERE user_name LIKE ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            String password = rs.getString("password");
            if (password.equals(inputPassword))
                return true;
        }
        rs.close();
        ps.close();
        return false;
    }
}
