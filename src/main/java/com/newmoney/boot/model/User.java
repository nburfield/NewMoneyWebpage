package com.newmoney.boot.model;

public class User {
	public User() {}
	
	public User(String firstname, String lastname, String password) {
		super();
		setFirstName(firstname);
		setLastName(lastname);
		setPassword(password);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private long id;
	private String firstName;
	private String lastName;
	private String password;	
}
