package com.newmoney.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class User {
	public User() {}
	
	public User(String email, String firstname, String lastname, byte [] encryptedPassword, byte [] salt, int role) {
		super();
		setEmail(email);
		setFirstName(firstname);
		setLastName(lastname);
		setEncryptedPassword(encryptedPassword);
		setSalt(salt);
		setRole(role);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(byte[] encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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


	@Id
	@Column(nullable = false, length = 30)
	private String email;
	
	@Column(nullable = false, length = 30)
	private String firstName;
	
	@Column(nullable = false, length = 30)
	private String lastName;
	
	@Lob @Column(nullable = false)
	private byte[] encryptedPassword;
	
	@Lob @Column(nullable = false)
	private byte[] salt;
	
	@Column(nullable = false)
	private int role;
}
