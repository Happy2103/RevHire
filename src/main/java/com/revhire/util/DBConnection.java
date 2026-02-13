package com.revhire.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/ORCLPDB.MSHOME.NET";
    private static final String USER = "revhire";
    private static final String PASSWORD = "revhire123";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Oracle DB Connected Successfully ✅");
            return conn;
        } catch (SQLException e) {
            System.out.println("DB Connection Failed ❌");
            e.printStackTrace();
            return null;
        }
    }
}
