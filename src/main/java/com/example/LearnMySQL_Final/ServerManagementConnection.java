package com.example.LearnMySQL_Final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServerManagementConnection {
	Connection con;
	public ServerManagementConnection() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://146.141.21.143:3306/SERVER","carl","carl");  
		}catch(Exception e){
			System.out.println(e);
			}
	}
	
	public boolean isFirstTime(String Student_no) {
		
		try {
			Statement stmt=con.createStatement();  
			
			ResultSet rs=stmt.executeQuery("select * from STUDENT WHERE STUDENT_NO = " + Student_no);  
			
			if(rs.isBeforeFirst()) {
				return true;
			}
			
			return false;
		}
	catch(Exception e){
		System.out.println(e);
		}
		
			return false;
	}
	
	public boolean addStudentToStudentTableInDatabase(Person p) {
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			String Student_name = p.getName();
			String Student_email = p.getEmail();
			
			String query = "INSERT INTO STUDENT (STUDENT_NO, STUDENT_NAME,STUDENT_EMAIL, STUDENT_DATABASE)" + "VALUES(?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString (1, Student_no);
		      preparedStmt.setString (2, Student_name);
		      preparedStmt.setString   (3, Student_email);
		      preparedStmt.setString (4, "d"+Student_no);
			int rs = preparedStmt.executeUpdate();
			
			System.out.println(rs);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
					}
	}
	public boolean addStudentDatabase(Person p) {
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			String query = "CREATE DATABASE " + "d" + Student_no;

		      

			int rs = stmt.executeUpdate(query);
			if(rs == 1) {
				
				stmt=con.createStatement();
				
				String query3 = "CREATE USER 'd" + Student_no+"'@localhost IDENTIFIED BY 'd" + Student_no +"'";
				String query2 = "CREATE USER 'd" + Student_no+"'@'%' IDENTIFIED BY 'd" + Student_no +"'";
				String query4 = "GRANT ALL PRIVILEGES ON " + "d" + Student_no +".* TO " + "'d" + Student_no + "'@'localhost'";
				String query5 = "GRANT ALL PRIVILEGES ON " + "d" + Student_no +".* TO " + "'d" + Student_no + "'@'%'"; 
				System.out.println(query2);
				System.out.println(query3);
				System.out.println(query4);
				System.out.println(query5);
				stmt.executeUpdate(query2);
				stmt.executeUpdate(query3);
				stmt.executeUpdate(query4);
				stmt.executeUpdate(query5);
			}
			
			System.out.println(rs);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
					}
	}
	
	public boolean addStudentHistoryQuery(Person p, String query, String Query_name) {
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			
			String query2 = "INSERT INTO HISTORY (STUDENT_NO, HISTORY_QUERY_NAME,HISTORY_QUERY)" + "VALUES(?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query2);
			preparedStmt.setString (1, Student_no);
		      preparedStmt.setString (2, Query_name);
		      preparedStmt.setString  (3, query);
			int rs = preparedStmt.executeUpdate();
			
			System.out.println(rs);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
					}
	}
	
	public triplet getStudentHistoryQuery(Person p) {
		ResultSet rs =null;
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			
			String query2 = "SELECT HISTORY_QUERY_NAME,HISTORY_QUERY FROM HISTORY WHERE STUDENT_NO =" + Student_no;
		
			rs = stmt.executeQuery(query2);
			
			return new triplet(true, rs, "");
		}catch (Exception e) {
			System.out.println(e);
			return new triplet(false,rs,e.toString());
					}
	}
	
	public triplet getStudentHistoryQueryWithSearch(Person p,String search) {
		ResultSet rs =null;
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			
			String query2 = "SELECT HISTORY_QUERY_NAME,HISTORY_QUERY FROM HISTORY WHERE STUDENT_NO =" + Student_no + " and HISTORY_QUERY LIKE '%" +search +"%'";
			System.out.println(query2);
			rs = stmt.executeQuery(query2);
			
			return new triplet(true, rs, "");
		}catch (Exception e) {
			System.out.println(e);
			return new triplet(false,rs,e.toString());
					}
	}
	
	
}
