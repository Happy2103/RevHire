package com.revhire.app;

import com.revhire.util.DBConnection;
import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getConnection();
            System.out.println("Oracle DB Connected Successfully ✅");
            con.close();
        } catch (Exception e) {
            System.out.println("DB Connection Failed ❌");
            e.printStackTrace();
        }
    }
}
