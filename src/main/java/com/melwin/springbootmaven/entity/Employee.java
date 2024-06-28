package com.melwin.springbootmaven.entity;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
	@Column(name = "id")
    private String id;
    
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "Salary")
	private Float Salary;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

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
		return Salary;
	}

	public void setSalary(Float salary) {
		Salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
    
    

}