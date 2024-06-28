package com.melwin.springbootmaven.dto;

public class EmployeeDTO {

	private String id;
	private String name;
	private String email;
	private String position;
	private Float salary;
	private String department;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public String getDepartmentId() {
		return department;
	}
	public void setDepartmentId(String departmentId) {
		this.department = departmentId;
	}
	
}
