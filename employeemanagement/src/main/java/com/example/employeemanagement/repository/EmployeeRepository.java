package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByFullNameContainingIgnoreCase(String fullName);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);

    long countByStatus(EmployeeStatus status);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.joiningDate >= :startDate")
    long countRecentlyAdded(@Param("startDate") LocalDate startDate);

    @Query("SELECT e FROM Employee e ORDER BY e.createdAt DESC LIMIT 5")
    List<Employee> findRecentlyAddedEmployees();
}