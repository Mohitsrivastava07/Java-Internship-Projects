-- ============================================================
-- Employee Management System - Database Schema & Seed Data
-- ============================================================

CREATE DATABASE IF NOT EXISTS test_employee_management_db;
USE test_employee_management_db;

DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    location    VARCHAR(150)
);

CREATE TABLE employees (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    email           VARCHAR(100) NOT NULL UNIQUE,
    phone           VARCHAR(15),
    designation     VARCHAR(50) NOT NULL,
    salary          DECIMAL(12,2) NOT NULL,
    date_of_joining DATE,
    department_id   BIGINT NOT NULL,
    CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id) REFERENCES departments(id)
        ON DELETE RESTRICT
);

-- ---- Seed data ----
INSERT INTO departments (name, location) VALUES
('Engineering', 'Bangalore'),
('Human Resources', 'Mumbai'),
('Sales', 'Delhi'),
('Finance', 'Pune');

INSERT INTO employees (first_name, last_name, email, phone, designation, salary, date_of_joining, department_id) VALUES
('Aarav', 'Sharma', 'aarav.sharma@advorix.tech', '9876543210', 'Software Engineer', 65000.00, '2023-01-15', 1),
('Priya', 'Verma', 'priya.verma@advorix.tech', '9876543211', 'Senior Software Engineer', 95000.00, '2021-06-01', 1),
('Rohan', 'Gupta', 'rohan.gupta@advorix.tech', '9876543212', 'HR Manager', 70000.00, '2020-03-10', 2),
('Sneha', 'Iyer', 'sneha.iyer@advorix.tech', '9876543213', 'Sales Executive', 45000.00, '2022-09-20', 3),
('Vikram', 'Singh', 'vikram.singh@advorix.tech', '9876543214', 'Finance Analyst', 55000.00, '2023-11-05', 4);