package com.revhire.dao;

import com.revhire.model.Application;
import com.revhire.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // Apply for a job
    public boolean apply(int userId, int jobId) {
        String sql = "INSERT INTO applications (user_id, job_id, status) VALUES (?, ?, 'Applied')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, jobId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // View all applications of a specific user
    public List<Application> getApplicationsByUser(int userId) {
        List<Application> applications = new ArrayList<>();

        String sql =
                "SELECT a.application_id, a.status, a.applied_date, " +
                        "j.title AS job_title, e.company_name " +
                        "FROM applications a " +
                        "JOIN jobs j ON a.job_id = j.job_id " +
                        "JOIN employers e ON j.employer_id = e.employer_id " +
                        "WHERE a.user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Application app = new Application();
                app.setApplicationId(rs.getInt("application_id"));
                app.setStatus(rs.getString("status"));
                app.setJobTitle(rs.getString("job_title"));
                app.setCompanyName(rs.getString("company_name"));
                app.setAppliedDate(rs.getDate("applied_date"));

                applications.add(app);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }


    // View all applications for a specific job (Employer view)
    public List<Application> getApplicationsByJob(int jobId) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE job_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Application app = new Application();
                app.setApplicationId(rs.getInt("application_id"));
                app.setUserId(rs.getInt("user_id"));
                app.setJobId(rs.getInt("job_id"));
                app.setStatus(rs.getString("status"));
                applications.add(app);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    // Optional: Update application status (Shortlist / Reject)
    public boolean updateApplicationStatus(int applicationId, String status) {
        String sql = "UPDATE applications SET status=? WHERE application_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, applicationId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
