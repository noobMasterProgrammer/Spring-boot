package com.noobmasterprogrammer.async.model;

public class User {
	private int id;
	private String mail;
	private String name;
	private String company;
	private String role;
	public User() {
		this.id=1;
		this.mail="ghoshabhishek@gmail.com";
		this.name="Abhishek Ghosh";
		this.company="TCS";
		this.role="Developer";
	}
	public User(Credential credential,Description description) {
		this.id= credential.getId();
		this.mail=credential.getMail();
		this.name=description.getName();
		this.company=description.getCompany();
		this.role=description.getRole();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", mail=" + mail + ", name=" + name + ", company=" + company + ", role=" + role + "]";
	}
	
	
}
