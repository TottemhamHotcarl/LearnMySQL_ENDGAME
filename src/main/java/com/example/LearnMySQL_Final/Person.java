package com.example.LearnMySQL_Final;



public class Person {
	public static final String STUDENT = "1";
	public static final String STAFF = "2";
	String name;
	String firstname; 
	String lastname;
	String email;
	String id;
	String access;
	
	public Person() {
		
	}
	
	public Person(String name, String first, String last,String email, String id, String access) {
		this.name = name;
		this.firstname = first;
		this.lastname = last;
		this.email = email;
		this.id = id;
		this.access = access;
	}
	
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAccessLevel(String access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", email=" + email + ", id=" + id + ", access=" + access + "]";
	}

	
	

}
