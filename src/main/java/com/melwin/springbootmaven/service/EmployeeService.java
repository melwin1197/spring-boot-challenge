package com.melwin.springbootmaven.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melwin.springbootmaven.dto.EmployeeDTO;
import com.melwin.springbootmaven.entity.Department;
import com.melwin.springbootmaven.entity.Employee;
import com.melwin.springbootmaven.repository.DepartmentRepository;
import com.melwin.springbootmaven.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    @Transactional
    public EmployeeDTO getEmployeeDetails(String id) {
         Optional<Employee> employee = employeeRepository.findById(id);
         if(employee.isPresent()) {
        	 return convertToDTO(employee.get());
         }
         return null;    }

    @Transactional
    public List<EmployeeDTO> getEmployeesByDepartment(String departmentId) {
    	 Optional<Department> department = departmentRepository.findById(departmentId);
         if (department.isPresent()) {
             return department.get().getEmployees().stream().map(this::convertToDTO).collect(Collectors.toList());
         }
         return List.of();
    }

    @Transactional
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
    	Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartmentId());
        if (department.isPresent()) {
            Employee employee = new Employee();
            try {
				BeanUtils.copyProperties(employee, employeeDTO);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
            employee.setDepartment(department.get());
            return convertToDTO(employeeRepository.save(employee));
        }
        throw new RuntimeException("Department not found");
    }

    @Transactional
    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }
    
    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
			BeanUtils.copyProperties(employeeDTO, employee);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
        employeeDTO.setDepartmentId(employee.getDepartment().getId());
        return employeeDTO;
    }
    
}


