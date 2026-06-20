package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStats {
    private Long totalEmployees;
    private Long activeEmployees;
    private Long totalDepartments;
    private Long recentlyAddedEmployeesCount;
}