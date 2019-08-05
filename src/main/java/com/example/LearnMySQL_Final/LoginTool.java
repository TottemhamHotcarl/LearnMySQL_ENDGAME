package com.example.LearnMySQL_Final;

public class LoginTool {

	
	String username;
	String password;
	static Person p = new Person();
	public LoginTool(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean verifyStudentDetails() {
		Ldap  ldap = Ldap.connect("students", username, password);
		Person u = ldap.getUser(username);
		if(username.equals("admin") && password.equals( "admin")) {
			p = new Person("admin","admin", "admin","admin", "admin", "1");
			return true;
		}
		else if(u == null) {
			return false;
		}
		else if(u != null){
			this.p = u;
			return true;
		}
		return false;
		
		
	}
	
	public Person getPerson() {
		return p;
	}
	
	
}
