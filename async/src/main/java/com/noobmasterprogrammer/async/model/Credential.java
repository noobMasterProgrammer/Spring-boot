package com.noobmasterprogrammer.async.model;

public class Credential {
	private int id;
	private String mail;
	public Credential(int id, String mail) {
		this.id = id;
		this.mail = mail;
	}
	public Credential() {
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
	@Override
	public String toString() {
		return "Credential [id=" + id + ", mail=" + mail + "]";
	}
	
}
