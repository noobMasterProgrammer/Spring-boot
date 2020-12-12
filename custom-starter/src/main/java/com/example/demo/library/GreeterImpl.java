package com.example.demo.library;

public class GreeterImpl implements Greeter {

	private String companyName;
	private String role;
	private String name;
	private String password;
	private String designation;
	
	
	public GreeterImpl(String companyName, String role, String name, String password, String designation) {
		this.companyName = companyName;
		this.role = role;
		this.name = name;
		this.password = password;
		this.designation = designation;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getDesignation() {
		return designation;
	}
	@Override
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public void greet() {
		System.out.println("Company Name is " + companyName + "\nRole is " + role + "\nName is " + name + "\nPassword is "
				+ password + "\nDesignation is " + designation);
	}
	

}
