package com.newmoney.boot.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class UserValidation {
	public UserValidation() {}
	
	public UserValidation(String email, String firstname, String lastname, String password) {
		setEmail(email);
		setFirstName(firstname);
		setLastName(lastname);
		setPassword(password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public void setAuthenticate() {
		try {
			salt = generateSalt();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			encryptedPassword = getEncryptedPassword(getPassword(), getSalt());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getAuthenticate(byte[] encryptedPassword, byte[] salt) {
		try {
			return authenticate(getPassword(), encryptedPassword, salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
		   		throws NoSuchAlgorithmException, InvalidKeySpecException {
	  // Encrypt the clear-text password using the same salt that was used to
	  // encrypt the original password
	  byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
	  // Authentication succeeds if encrypted password that the user entered
	  // is equal to the stored hash
	  return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}
	
	private byte[] getEncryptedPassword(String password, byte[] salt)
			 		throws NoSuchAlgorithmException, InvalidKeySpecException {
		  // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
		  // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
		  String algorithm = "PBKDF2WithHmacSHA1";
		  // SHA-1 generates 160 bit hashes, so that's what makes sense here
		  int derivedKeyLength = 160;
		  // Pick an iteration count that works for you. The NIST recommends at
		  // least 1,000 iterations:
		  // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
		  // iOS 4.x reportedly uses 10,000:
		  // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
		  int iterations = 20000;
		
		  KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
		 
		  SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		 
		  return f.generateSecret(spec).getEncoded();
	}
	
	private byte[] generateSalt() throws NoSuchAlgorithmException {
		  // VERY important to use SecureRandom instead of just Random
		  SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		 
		  // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		  byte[] salt = new byte[8];
		  random.nextBytes(salt);
		  return salt;
	}

	private String email;
	private String firstName;
	private String lastName;
	private String password;
	
	private byte [] salt;
	private byte [] encryptedPassword;
	
	public byte[] getSalt() {
		return salt;
	}

	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}
}
