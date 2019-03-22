package com.example.LearnMySQL_Final;
import java.sql.*;  
public class testConnect {

	
	public testConnect(){
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://160.153.129.212/i4871219_wp1","fcchm6ydp742","PraveshCom18@");  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from TEST");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		con.close();  
		}catch(Exception e){
			System.out.println(e);}  
		}  
}
	
	

