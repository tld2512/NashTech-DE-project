package com.longdt.finalproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        String dbName, dbUserName, dbPassword;
        Connection connection = null;
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/";
            String utf8 = "?useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false";

            dbName = "final_project";
            dbUserName = "root";
            dbPassword = "password";

            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL + dbName + utf8, dbUserName, dbPassword);
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
