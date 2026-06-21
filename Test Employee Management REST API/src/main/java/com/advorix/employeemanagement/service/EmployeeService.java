package com.advorix.employeemanagement.service;

import com.advorix.employeemanagement.dto.EmployeeRequestDTO;
import com.advorix.employeemanagement.dto.EmployeeResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    EmployeeResponseDTO getEmployeeById(Long id);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto);

    void deleteEmployee(Long id);

    List<EmployeeResponseDTO> searchEmployees(String name, String designation,
                                              Long departmentId,
                                              BigDecimal minSalary,
                                              BigDecimal maxSalary);
}