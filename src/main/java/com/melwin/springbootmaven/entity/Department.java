package com.melwin.springbootmaven.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Department {

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "location")
	private String location;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Employee> employees;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	

}
