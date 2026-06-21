package com.advorix.employeemanagement.service;

import com.advorix.employeemanagement.dto.EmployeeRequestDTO;
import com.advorix.employeemanagement.dto.EmployeeResponseDTO;
import com.advorix.employeemanagement.exception.DuplicateResourceException;
import com.advorix.employeemanagement.exception.ResourceNotFoundException;
import com.advorix.employeemanagement.model.Department;
import com.advorix.employeemanagement.model.Employee;
import com.advorix.employeemanagement.repository.DepartmentRepository;
import com.advorix.employeemanagement.repository.EmployeeRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("An employee with email '" + dto.getEmail() + "' already exists");
        }

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", dto.getDepartmentId()));

        Employee employee = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .designation(dto.getDesignation())
                .salary(dto.getSalary())
                .dateOfJoining(dto.getDateOfJoining())
                .department(department)
                .build();

        Employee saved = employeeRepository.save(employee);
        return toResponseDTO(saved);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return toResponseDTO(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        // If email is changing, make sure the new one isn't already taken by someone else
        if (!employee.getEmail().equalsIgnoreCase(dto.getEmail())
                && employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("An employee with email '" + dto.getEmail() + "' already exists");
        }

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", dto.getDepartmentId()));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());
        employee.setDateOfJoining(dto.getDateOfJoining());
        employee.setDepartment(department);

        Employee updated = employeeRepository.save(employee);
        return toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeResponseDTO> searchEmployees(String name, String designation,
                                                     Long departmentId,
                                                     BigDecimal minSalary,
                                                     BigDecimal maxSalary) {
        Specification<Employee> spec = buildSearchSpecification(name, designation, departmentId, minSalary, maxSalary);
        return employeeRepository.findAll(spec)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Builds a dynamic WHERE clause based on whichever filter parameters were supplied.
    // Any parameter left null is simply skipped, so callers can mix and match filters.
    private Specification<Employee> buildSearchSpecification(String name, String designation,
                                                             Long departmentId,
                                                             BigDecimal minSalary,
                                                             BigDecimal maxSalary) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isBlank()) {
                String pattern = "%" + name.toLowerCase() + "%";
                Predicate firstNameMatch = cb.like(cb.lower(root.get("firstName")), pattern);
                Predicate lastNameMatch = cb.like(cb.lower(root.get("lastName")), pattern);
                predicates.add(cb.or(firstNameMatch, lastNameMatch));
            }
            if (designation != null && !designation.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("designation")), "%" + designation.toLowerCase() + "%"));
            }
            if (departmentId != null) {
                predicates.add(cb.equal(root.get("department").get("id"), departmentId));
            }
            if (minSalary != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("salary"), minSalary));
            }
            if (maxSalary != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("salary"), maxSalary));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private EmployeeResponseDTO toResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .dateOfJoining(employee.getDateOfJoining())
                .departmentId(employee.getDepartment().getId())
                .departmentName(employee.getDepartment().getName())
                .build();
    }
}