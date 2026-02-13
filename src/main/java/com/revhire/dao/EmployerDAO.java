package com.revhire.dao;

import com.revhire.model.Employer;
import com.revhire.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployerDAO {

    // Register employer
    public boolean register(Employer emp) {
        String sql = "INSERT INTO employers (company_name, email, password, industry, location) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getCompanyName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPassword());
            ps.setString(4, emp.getIndustry());
            ps.setString(5, emp.getLocation());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login employer
    public Employer login(String email, String password) {
        String sql = "SELECT * FROM employers WHERE email=? AND password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employer emp = new Employer();
                emp.setEmployerId(rs.getInt("employer_id"));
                emp.setCompanyName(rs.getString("company_name"));
                emp.setEmail(rs.getString("email"));
                emp.setIndustry(rs.getString("industry"));
                emp.setLocation(rs.getString("location"));
                return emp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Change Password
    public boolean changePassword(int employerId, String oldPassword, String newPassword) {
        String sqlCheck = "SELECT * FROM employers WHERE employer_id=? AND password=?";
        String sqlUpdate = "UPDATE employers SET password=? WHERE employer_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {

            psCheck.setInt(1, employerId);
            psCheck.setString(2, oldPassword);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) return false;

            psUpdate.setString(1, newPassword);
            psUpdate.setInt(2, employerId);
            return psUpdate.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
