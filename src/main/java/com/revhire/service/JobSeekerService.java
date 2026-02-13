package com.revhire.service;

import com.revhire.dao.ApplicationDAO;

import java.util.Scanner;

public class JobSeekerService {

    private final ApplicationDAO applicationDAO = new ApplicationDAO();
    private final Scanner sc = new Scanner(System.in);

    public void applyForJob(int userId) {
        System.out.print("Enter Job ID to apply: ");
        int jobId = sc.nextInt();

        boolean success = applicationDAO.apply(userId, jobId);

        if (success) {
            System.out.println("✅ Job applied successfully!");
        } else {
            System.out.println("❌ Failed to apply for job.");
        }
    }
}
