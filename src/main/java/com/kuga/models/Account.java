package com.kuga.models;

// comment this class

/**
 * @created-date 2021-10-14
 * @author longnguyen
 * */


public class Account {
	private String name;
	private String email;
	
	
	public Account() {
		super();
	}
	public Account(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	// return name
	public String getName() {
		return name;
	}
	// set name
	public void setName(String name) {
		this.name = name;
	}
	// return email
	public String getEmail() {
		return email;
	}
	// set email
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", email=" + email + "]";
	}
	
}
