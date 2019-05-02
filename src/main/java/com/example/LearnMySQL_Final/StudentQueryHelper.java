package com.example.LearnMySQL_Final;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.Grid;

public class StudentQueryHelper {

	
		Connection con;
		public StudentQueryHelper(Person p) {
			if(!p.getId().equals("admin")) {
				String user = "d" + p.getId();
				try{  
					Class.forName("com.mysql.jdbc.Driver");  
					con=DriverManager.getConnection(  
					"jdbc:mysql://146.141.21.143:3306/" +user ,user ,user);  
				}catch(Exception e){
					System.out.println("Pizza");
					System.out.println(e);
					}
			}
			else if(p.getId().equals("admin")) {
				String user = "carl";
				try{  
					Class.forName("com.mysql.jdbc.Driver");  
					con=DriverManager.getConnection(  
					"jdbc:mysql://146.141.21.143:3306/" +"SERVER" ,user ,user);  
				}catch(Exception e){
					System.out.println("Pizza");
					System.out.println(e);
					}
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
		
		public Grid queryShowTable() throws SQLException {
			Grid<HashMap<String, String>> grid4 = null;
			List<HashMap<String, String>> rows = new ArrayList<>();
			DatabaseMetaData meta = con.getMetaData();
			 ResultSet res = meta.getTables(null, null, null, 
			         new String[] {"TABLE"});
			      System.out.println("List of tables: "); 
			      while (res.next()) {
			         System.out.println(res.getString("TABLE_NAME"));
			            	 HashMap<String, String> fakeBean = new HashMap<>();
			            	fakeBean.put("TABLE_NAME", res.getString("TABLE_NAME"));
			                 rows.add(fakeBean);
			            
			          
			      }
			      grid4 = new Grid<>();
			      grid4.setItems(rows);
			      res.close();
			      HashMap<String, String> q = rows.get(0);
		            for (Map.Entry<String, String> entry : q.entrySet()) {
		                grid4.addColumn(h -> h.get(entry.getKey())).setCaption(entry.getKey());
		            }
			      return grid4;
		}
		

}