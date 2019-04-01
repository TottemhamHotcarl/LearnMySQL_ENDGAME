package com.example.LearnMySQL_Final;
import java.sql.*;  
public class testConnect {

	
	public testConnect(){
	}
	public String test() {
		String t ="qwqwqwq";
		Connection con;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://146.141.21.143:3306/SERVER","carl","carl");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from STUDENT");  
			
			
			
			while(rs.next()) {
			t = t + 	rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3);
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			}
			con.close(); 
			}catch(Exception e){
				System.out.println(e);
				}
		
		return t;
		
			}  
	
	}
	
	

	
	

