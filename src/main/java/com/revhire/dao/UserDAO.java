package com.revhire.dao;

import com.revhire.model.User;
import com.revhire.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Register a new user
    public boolean register(User user) {
        String sql = "INSERT INTO users (full_name, email, password, phone, experience_years) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setInt(5, user.getExperienceYears());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setExperienceYears(rs.getInt("experience_years"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Change Password
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        String sqlCheck = "SELECT * FROM users WHERE user_id=? AND password=?";
        String sqlUpdate = "UPDATE users SET password=? WHERE user_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {

            psCheck.setInt(1, userId);
            psCheck.setString(2, oldPassword);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) return false; // old password wrong

            psUpdate.setString(1, newPassword);
            psUpdate.setInt(2, userId);
            return psUpdate.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
