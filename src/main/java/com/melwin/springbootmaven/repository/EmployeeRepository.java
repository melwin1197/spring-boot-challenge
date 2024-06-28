package com.melwin.springbootmaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.melwin.springbootmaven.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	List<Employee> findByDepartmentId(String departmentId);
}
