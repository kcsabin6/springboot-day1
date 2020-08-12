package com.rab3tech.rest.vo;

public class EmployeeVO {
	
	private int eid;
	private String name;
	private String email;
	private int salary;
	
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "EmployeeVO [eid=" + eid + ", name=" + name + ", email=" + email + ", salary=" + salary + "]";
	}
	
	
	

}
