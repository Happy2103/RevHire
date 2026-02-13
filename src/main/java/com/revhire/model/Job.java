package com.revhire.model;

public class Job {
    private int jobId;
    private int employerId;
    private String title;
    private String description;
    private String location;
    private int experienceRequired;

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public int getEmployerId() { return employerId; }
    public void setEmployerId(int employerId) { this.employerId = employerId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getExperienceRequired() { return experienceRequired; }
    public void setExperienceRequired(int experienceRequired) {
        this.experienceRequired = experienceRequired;
    }
}
