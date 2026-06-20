package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.DashboardStats;
import com.example.employeemanagement.entity.EmployeeStatus;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public DashboardStats getDashboardStats() {
        long totalEmployees = employeeRepository.count();
        long activeEmployees = employeeRepository.countByStatus(EmployeeStatus.ACTIVE);
        long totalDepartments = departmentRepository.count();
        long recentlyAdded = employeeRepository.countRecentlyAdded(LocalDate.now().minusDays(30));

        return DashboardStats.builder()
                .totalEmployees(totalEmployees)
                .activeEmployees(activeEmployees)
                .totalDepartments(totalDepartments)
                .recentlyAddedEmployeesCount(recentlyAdded)
                .build();
    }
}