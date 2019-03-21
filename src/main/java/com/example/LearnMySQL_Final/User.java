package com.example.LearnMySQL_Final;

public class User {

	public static Person person = new Person();
	
	public void setperson(Person n) {
		person = n;
	}
	
	public User(Person n) {
		person = n;
	}
	public User() {
	}
	
}
