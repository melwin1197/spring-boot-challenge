package com.melwin.springbootmaven.controller;

import com.melwin.springbootmaven.dto.EmployeeDTO;
import com.melwin.springbootmaven.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeDetails(@PathVariable String employeeId) {
        return employeeService.getEmployeeDetails(employeeId);
    }

    @GetMapping("/department/{departmentId}")
    public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable String departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
