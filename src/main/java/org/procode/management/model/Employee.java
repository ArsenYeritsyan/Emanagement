package org.procode.management.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "employee")
@RequiredArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String department;

	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="manager_id")
	private Manager manager;


	public Employee(String name, String department) {
		this.name = name;
		this.department = department;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, department);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Employee employee = (Employee) o;
		return Objects.equals(name, employee.name) && Objects.equals(department, employee.department);
	}

	@Override
	public String toString() {
		return "Employee{" +
			"name='" + name + '\'' +
			", department='" + department + '\'' +
			'}';
	}
}
