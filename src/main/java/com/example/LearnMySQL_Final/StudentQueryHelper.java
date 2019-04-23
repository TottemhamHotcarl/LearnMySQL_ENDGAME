package com.example.LearnMySQL_Final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentQueryHelper {

	
		Connection con;
		public StudentQueryHelper(Person p) {
			String user = "d" + p.getId();
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				con=DriverManager.getConnection(  
				"jdbc:mysql://146.141.21.143:3306/" +user ,user ,user);  
			}catch(Exception e){
				System.out.println(e);
				}
	
	
		}
		
		public String queryUpdateRun(String query) {
			if(!query.toUpperCase().contains("SELECT")) {
				try {
					Statement stmt=con.createStatement();
					int rs = stmt.executeUpdate(query);
					return "Query OK, " + rs + " rows affected";
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					return e.getMessage().toString();
				} 
				
			}
			return "Something went wrong";
		}
		
		public triplet querySelectRun(String query) {
			ResultSet rs = null;
			if(query.toUpperCase().contains("SELECT")) {
				try {
					Statement stmt=con.createStatement();
					 rs = stmt.executeQuery(query);
					return new triplet(true, rs, "");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					return new triplet(false, rs, e.getMessage().toString());
				} 
				
			}
			return new triplet(false,rs, "Something went wrong");
			
		}
		
		

}