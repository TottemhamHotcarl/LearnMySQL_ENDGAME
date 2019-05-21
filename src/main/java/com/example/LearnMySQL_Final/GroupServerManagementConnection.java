package com.example.LearnMySQL_Final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupServerManagementConnection {

	Connection con;
	public GroupServerManagementConnection() {
		try{  
			//Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://146.141.21.143:3306/SERVER","carl","carl");  
		}catch(Exception e){
			System.out.println(e);
			}
	}
	
	public GroupServerManagementConnection(String ip) {
		try{  
			//Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://" + ip+":3306/SERVER","root","");  
		}catch(Exception e){
			System.out.println(e);
			}
	}
	

	
	public void finalize(){
		System.out.println("END");
		try {
			con.close();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public boolean addGroup(Person p, String Group_name) {
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			String database = "d" + Student_no;
			
			String query = "INSERT INTO GROUPS (GROUP_ADMIN, GROUP_NAME, GROUP_DATABASE)" + "VALUES(?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString (1, Student_no);
		      preparedStmt.setString (2, Group_name);
		      preparedStmt.setString   (3, database);
		  		      
		      
		      
		
			int rs = preparedStmt.executeUpdate();
			
			System.out.println(rs);
			return true;
		}catch (Exception e) {
			System.out.println("wow"+e);
			return false;
					}
	}

	
	
	
	public triplet alreadyCreatedGroup(Person p) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			String Student_no = p.getId();
			
			String query = "SELECT * FROM STUDENT_GROUP WHERE STUDENT_NO = " + Student_no;
			
			ResultSet rst = stmt.executeQuery(query);
			
			
			
			if(rst.next()) {
				//rs.previous();
				String id = rst.getString("GROUP_ID");
				ResultSet rs = stmt.executeQuery("SELECT * FROM GROUPS WHERE GROUP_ID = " + id);
				rs.next();
				GroupObject go = new GroupObject(rs.getString("GROUP_ID"), rs.getString("GROUP_ADMIN"), rs.getString("GROUP_NAME"), rs.getString("GROUP_DATABASE"));
				return new triplet(true, rs,"" );
			}
			return new triplet(false,null,"");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new triplet(false,null,"Something went wrong");
		}
		
	
	}
	
	
	
	public triplet getGroupMembers(String Group_ID) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			
			String query = "SELECT STUDENT_NO, STUDENT_EMAIL, STUDENT_NAME FROM STUDENT WHERE STUDENT_NO IN (SELECT STUDENT_NO FROM STUDENT_GROUP WHERE GROUP_ID = " + Group_ID + ")";
			ResultSet rs = stmt.executeQuery(query);
			
			

				return new triplet(true, rs,"" );
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new triplet(false,null,"Something went wrong");
		}
		
	
	}
	
	
	
	
	
	public boolean addMember(String Student_no) {
		
		try {
			Statement stmt=con.createStatement();
			
			String query = "INSERT INTO STUDENT_GROUP (GROUP_ID, STUDENT_NO)" + "VALUES(?,?)";
			System.out.println(query);
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString (2, Student_no);
		      GroupObject go = new GroupObject();
		      preparedStmt.setString   (1, go.getID());
			int rs = preparedStmt.executeUpdate();
			
			System.out.println(rs);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
					}
	
	}
	
	
public boolean removeMember(String Student_no) {
		
		try {
			Statement stmt=con.createStatement();
			
			String query = "DELETE FROM STUDENT_GROUP WHERE STUDENT_NO =" + Student_no;
			System.out.println(query);
			PreparedStatement preparedStmt = con.prepareStatement(query);
			int rs = preparedStmt.executeUpdate();
			
			System.out.println(rs);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
					}

	}
	
	
}
