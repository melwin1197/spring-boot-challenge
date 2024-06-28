package com.melwin.springbootmaven.service;

import com.melwin.springbootmaven.dto.DepartmentDTO;
import com.melwin.springbootmaven.entity.Department;
import com.melwin.springbootmaven.entity.Employee;
import com.melwin.springbootmaven.repository.DepartmentRepository;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeService employeeService;

    @Transactional
	public List<DepartmentDTO> getAllDepartments() {
		return departmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

    @Transactional
	public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department();
		try {
			BeanUtils.copyProperties(department, departmentDTO);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return convertToDTO(departmentRepository.save(department));
	}

	private DepartmentDTO convertToDTO(Department department) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		try {
			BeanUtils.copyProperties(departmentDTO, department);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		departmentDTO.setEmployees(Objects.nonNull(department.getEmployees()) ? department.getEmployees().stream()
				.map(emp -> employeeService.convertToDTO(emp)).collect(Collectors.toList()) : List.of());
		return departmentDTO;
	}
}
