package com.melwin.springbootmaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melwin.springbootmaven.dto.DepartmentDTO;
import com.melwin.springbootmaven.service.DepartmentService;

@RestController
@CrossOrigin
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
    
    @PostMapping
    public DepartmentDTO addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.addDepartment(departmentDTO);
    }
}
