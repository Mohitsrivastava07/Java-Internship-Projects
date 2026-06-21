package com.advorix.employeemanagement.service;

import com.advorix.employeemanagement.dto.DepartmentDTO;
import com.advorix.employeemanagement.exception.ResourceNotFoundException;
import com.advorix.employeemanagement.model.Department;
import com.advorix.employeemanagement.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department department = Department.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
        Department saved = departmentRepository.save(department);
        return toDTO(saved);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        return toDTO(department);
    }

    @Override
    @Transactional
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());
        return toDTO(departmentRepository.save(department));
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        departmentRepository.delete(department);
    }

    private DepartmentDTO toDTO(Department department) {
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .location(department.getLocation())
                .build();
    }
}