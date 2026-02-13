package com.revhire.dao;

import com.revhire.model.Job;
import com.revhire.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    // ---------- Employer: Post Job ----------
    public boolean postJob(Job job) {
        String sql = "INSERT INTO jobs (employer_id, title, description, location, experience_required) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, job.getEmployerId());
            ps.setString(2, job.getTitle());
            ps.setString(3, job.getDescription());
            ps.setString(4, job.getLocation());
            ps.setInt(5, job.getExperienceRequired());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------- Job Seeker: View Jobs ----------
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT job_id, title, location, experience_required FROM jobs";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setTitle(rs.getString("title"));
                job.setLocation(rs.getString("location"));
                job.setExperienceRequired(rs.getInt("experience_required"));

                jobs.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobs;
    }
}
