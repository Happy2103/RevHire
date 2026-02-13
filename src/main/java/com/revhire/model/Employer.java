package com.revhire.model;

public class Employer {
    private int employerId;
    private String companyName;
    private String email;
    private String password;
    private String industry;
    private String location;

    // Getters and Setters
    public int getEmployerId() { return employerId; }
    public void setEmployerId(int employerId) { this.employerId = employerId; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
