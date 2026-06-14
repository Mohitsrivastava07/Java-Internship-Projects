package com.management.employeemanagement.service;

import java.util.List;
import com.management.employeemanagement.entity.Employee;

public interface EmployeeService {
    Employee save(Employee employee);

    List<Employee> getAll();

    Employee getById(Long id);

    Employee update(Long id, Employee employee);

    void delete(Long id);
}