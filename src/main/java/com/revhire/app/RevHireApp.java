package com.revhire.app;

import com.revhire.dao.UserDAO;
import com.revhire.dao.EmployerDAO;
import com.revhire.dao.JobDAO;
import com.revhire.dao.ApplicationDAO;
import com.revhire.model.Application;

import com.revhire.model.User;
import com.revhire.model.Employer;
import com.revhire.model.Job;

import java.util.Scanner;

public class RevHireApp {

    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;
    private static Employer loggedInEmployer = null;

    public static void main(String[] args) {
        System.out.println("=== Welcome to RevHire (Account Management) ===");
        boolean exit = false;

        while (!exit) {
            System.out.println("\nSelect Role:");
            System.out.println("1. Job Seeker");
            System.out.println("2. Employer");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    jobSeekerMenu();
                    break;
                case 2:
                    employerMenu();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("Thank you for using RevHire!");
    }

    // ---------------- Job Seeker Menu ----------------
    private static void jobSeekerMenu() {
        UserDAO userDAO = new UserDAO();
        JobDAO jobDAO = new JobDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();

        System.out.println("\n--- Job Seeker Menu ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. View Jobs");
        System.out.println("4. Apply Job");
        System.out.println("5. View My Applications");
        System.out.println("6. Change Password");
        System.out.println("7. Back");

        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {

            case 1: // Register
                User user = new User();
                System.out.print("Full Name: ");
                user.setFullName(scanner.nextLine());
                System.out.print("Email: ");
                user.setEmail(scanner.nextLine());
                System.out.print("Password: ");
                user.setPassword(scanner.nextLine());
                System.out.print("Phone: ");
                user.setPhone(scanner.nextLine());
                System.out.print("Experience Years: ");
                user.setExperienceYears(Integer.parseInt(scanner.nextLine()));

                if (userDAO.register(user))
                    System.out.println("Registration Successful ✅");
                else
                    System.out.println("Registration Failed ❌");
                break;

            case 2: // Login
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Password: ");
                String pwd = scanner.nextLine();

                User loggedUser = userDAO.login(email, pwd);
                if (loggedUser != null) {
                    loggedInUser = loggedUser;
                    System.out.println("Login Successful ✅ Welcome " + loggedInUser.getFullName());
                } else {
                    System.out.println("Invalid Credentials ❌");
                }
                break;

            case 3: // View Jobs
                var jobs = jobDAO.getAllJobs();
                if (jobs.isEmpty()) {
                    System.out.println("No jobs available.");
                } else {
                    for (var job : jobs) {
                        System.out.println("---------------------------");
                        System.out.println("Job ID: " + job.getJobId());
                        System.out.println("Title: " + job.getTitle());
                        System.out.println("Location: " + job.getLocation());
                        System.out.println("Experience Required: " + job.getExperienceRequired());
                    }
                    System.out.println("---------------------------");
                }
                break;

            case 4: // ✅ APPLY JOB
                if (loggedInUser == null) {
                    System.out.println("Please login first!");
                    break;
                }

                System.out.print("Enter Job ID to apply: ");
                int jobId = Integer.parseInt(scanner.nextLine());

                boolean applied = applicationDAO.apply(loggedInUser.getUserId(), jobId);

                if (applied)
                    System.out.println("Job Applied Successfully ✅");
                else
                    System.out.println("Failed to Apply Job ❌");

                break;

            case 5: // View My Applications
                if (loggedInUser == null) {
                    System.out.println("Please login first!");
                    break;
                }

                ApplicationDAO appDAO = new ApplicationDAO();
                var apps = appDAO.getApplicationsByUser(loggedInUser.getUserId());

                if (apps.isEmpty()) {
                    System.out.println("You have not applied for any jobs yet.");
                } else {
                    System.out.println("---- My Applications ----");
                    for (Application app : apps) {
                        System.out.println("Application ID : " + app.getApplicationId());
                        System.out.println("Company        : " + app.getCompanyName());
                        System.out.println("Role           : " + app.getJobTitle());
                        System.out.println("Status         : " + app.getStatus());
                        System.out.println("-------------------------");
                    }
                }
                break;


            case 6: // Change Password
                if (loggedInUser == null) {
                    System.out.println("Please login first!");
                    break;
                }
                System.out.print("Old Password: ");
                String oldPwd = scanner.nextLine();
                System.out.print("New Password: ");
                String newPwd = scanner.nextLine();

                if (userDAO.changePassword(loggedInUser.getUserId(), oldPwd, newPwd))
                    System.out.println("Password Changed ✅");
                else
                    System.out.println("Failed to change password ❌");
                break;

            case 7: // Back
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }

    // ---------------- Employer Menu ----------------
    private static void employerMenu() {
        EmployerDAO employerDAO = new EmployerDAO();
        JobDAO jobDAO = new JobDAO();

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Change Password");
        System.out.println("4. Post Job");
        System.out.println("5. View Applicants");
        System.out.println("6. Back");

        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {

            case 1: // Register
                Employer emp = new Employer();
                System.out.print("Company Name: ");
                emp.setCompanyName(scanner.nextLine());
                System.out.print("Email: ");
                emp.setEmail(scanner.nextLine());
                System.out.print("Password: ");
                emp.setPassword(scanner.nextLine());
                System.out.print("Industry: ");
                emp.setIndustry(scanner.nextLine());
                System.out.print("Location: ");
                emp.setLocation(scanner.nextLine());

                if (employerDAO.register(emp))
                    System.out.println("Employer Registered ✅");
                else
                    System.out.println("Registration Failed ❌");
                break;

            case 2: // Login
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Password: ");
                String pwd = scanner.nextLine();

                Employer loggedEmp = employerDAO.login(email, pwd);
                if (loggedEmp != null) {
                    loggedInEmployer = loggedEmp;
                    System.out.println("Login Successful ✅ Welcome " + loggedInEmployer.getCompanyName());
                } else {
                    System.out.println("Invalid Credentials ❌");
                }
                break;

            case 3: // Change Password
                if (loggedInEmployer == null) {
                    System.out.println("Please login first!");
                    break;
                }
                System.out.print("Old Password: ");
                String oldPwd = scanner.nextLine();
                System.out.print("New Password: ");
                String newPwd = scanner.nextLine();

                if (employerDAO.changePassword(loggedInEmployer.getEmployerId(), oldPwd, newPwd))
                    System.out.println("Password Changed ✅");
                else
                    System.out.println("Failed to change password ❌");
                break;

            case 4: // Post Job
                if (loggedInEmployer == null) {
                    System.out.println("Please login first!");
                    break;
                }

                Job job = new Job();
                job.setEmployerId(loggedInEmployer.getEmployerId());

                System.out.print("Job Title: ");
                job.setTitle(scanner.nextLine());

                System.out.print("Description: ");
                job.setDescription(scanner.nextLine());

                System.out.print("Location: ");
                job.setLocation(scanner.nextLine());

                System.out.print("Experience Required: ");
                job.setExperienceRequired(Integer.parseInt(scanner.nextLine()));

                if (jobDAO.postJob(job))
                    System.out.println("Job Posted Successfully ✅");
                else
                    System.out.println("Failed to Post Job ❌");
                break;
            case 5: // View Applicants for a Job
                if (loggedInEmployer == null) {
                    System.out.println("Please login first!");
                    break;
                }

                System.out.print("Enter Job ID: ");
                int jobId = Integer.parseInt(scanner.nextLine());

                ApplicationDAO applicationDAO = new ApplicationDAO();
                var applications = applicationDAO.getApplicationsByJob(jobId);

                if (applications.isEmpty()) {
                    System.out.println("No applications found for this job.");
                } else {
                    System.out.println("---- Applicants ----");
                    for (Application app : applications) {
                        System.out.println("Application ID: " + app.getApplicationId());
                        System.out.println("User ID: " + app.getUserId());
                        System.out.println("Status: " + app.getStatus());
                        System.out.println("--------------------");
                    }
                }
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }
}
