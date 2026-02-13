# 💼 RevHire – Job Portal Console Application (Java + JDBC + Oracle)

## 📌 Project Overview

RevHire is a console-based Job Portal application built using **Core Java, JDBC, and Oracle Database**.
It allows Employers to post jobs and Job Seekers to search and apply for jobs.
The system manages users, job listings, and applications while ensuring data integrity and preventing duplicate applications.

This project demonstrates database connectivity, layered architecture (Model–DAO–Service–App), and real-world CRUD operations.

---

## 🚀 Features

### 👤 Job Seeker

* Register & Login
* View available jobs
* Apply for jobs
* View applied jobs
* Change password

### 🏢 Employer

* Register & Login
* Post new job openings
* Manage job listings
* Change password

### 🛡 System Features

* Prevents applying to the same job twice
* Auto-generated Job IDs using Oracle sequence
* Secure login validation
* JDBC-based database interaction
* Clean modular architecture

---

## 🏗 Project Architecture

```
com.revhire
│
├── app
│   └── RevHireApp.java            → Console UI & workflow control
│
├── model                          → Entity classes
│   ├── User.java
│   ├── Employer.java
│   ├── Job.java
│   └── Application.java
│
├── dao                            → Database interaction layer
│   ├── UserDAO.java
│   ├── EmployerDAO.java
│   ├── JobDAO.java
│   └── ApplicationDAO.java
│
├── util                           → Utilities
│   └── DBConnection.java
```

---

## 🗄 Database Tables

### USERS

Stores job seeker information

### EMPLOYERS

Stores employer/company details

### JOBS

Stores posted job details

### APPLICATIONS

Stores job applications submitted by users

---

## 🔄 Workflow

### Job Seeker Flow

1. Register/Login
2. View available jobs
3. Select job ID
4. Apply for job
5. Application stored in APPLICATIONS table

### Employer Flow

1. Register/Login
2. Post new job
3. Job stored in JOBS table
4. Applicants recorded in APPLICATIONS table

---

## 🛠 Tech Stack

* **Language:** Java (Core Java, OOP)
* **Database:** Oracle SQL
* **Connectivity:** JDBC
* **IDE:** IntelliJ IDEA
* **Version Control:** Git & GitHub

---

## 📊 Database Concepts Used

* Primary & Foreign Keys
* Sequences for auto-ID generation
* Constraints to prevent duplicate applications
* Joins to display job + company info
* Prepared Statements for SQL execution

---

## 💡 Key Learning Outcomes

* JDBC connection handling
* DAO design pattern
* Real-world relational database modeling
* Console-based application workflow design
* Preventing duplicate transactions using SQL constraints

---

## 📌 Future Enhancements

* Resume upload feature
* Email notifications
* Employer dashboard
* Search & filter jobs
* Web-based UI using Spring Boot

---

## 👨‍💻 Author

**Ashutosh Patro**

---

## ⭐ If you like this project

Give it a star on GitHub ⭐
