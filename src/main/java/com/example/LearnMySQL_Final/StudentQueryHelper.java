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

/**
 * This allow queries to be run in the querybox
 *
 */
public class StudentQueryHelper {

	
		String user;
		LayoutHelper lh = new LayoutHelper();
	
		public Connection getStudentConnection() {
			Connection conn;
			try {
				conn=DriverManager.getConnection(  
						"jdbc:mysql://146.141.21.143:3306/" +user ,user ,user);  
						return conn;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return null;
		}
		
		
		
		/**
		 * This connects the app to the students database
		 * @param p: A person object which stores the current user info
		 * 
		 */
		public StudentQueryHelper(Person p) {
			
			
			user = "d" + p.getId();
			
			
			
			
		}
		
		// Constructor for testing with Travis-CI
		/**
		 * This connects the app to the students database as while as change the ip address
		 * @param p: A person object which stores the current user info
		 * @param ip: The ip we want to change to
		 */
		public StudentQueryHelper(Person p, String ip) {
			Connection con = null;
			if(!p.getId().equals("admin")) {
				String user = "d" + p.getId();
				try{  
					//Class.forName("com.mysql.jdbc.Driver");  
					con=DriverManager.getConnection(  
					"jdbc:mysql://" + ip + ":3306/" +"TEST" ,"root" ,"");  
				}catch(Exception e){
					System.out.println("Pizza");
					System.out.println(e);
				}finally {
				    try { if (con != null) con.close(); } catch (Exception e) {};
				}
				
			}
			
		}
		
		
		/**
		 * This is the constructor when we connect to a group database
		 * @param database: the database of the group we want to connect to
		 */
		public StudentQueryHelper( String database) {
			
				Connection con = null;
				try{  
					//Class.forName("com.mysql.jdbc.Driver");  
					con=DriverManager.getConnection(  
							
					"jdbc:mysql://146.141.21.143:3306/" +database , database ,database);  
				}catch(Exception e){
					System.out.println("Pizza");
					System.out.println(e);
				}finally {
				    try { if (con != null) con.close(); } catch (Exception e) {};
				}
				
				
			}
			
		
		
		
		
		
		/**
		 * This run update query on the users databases
		 * @param query: the query which is going to run on the user's databases
		 * @return If valid query: Query OK, "number of row affected" rows affected. If invalid query: The error produced by the database
		 */
		public String queryUpdateRun(String query) {
			Connection con = null;
			Statement stmt = null;
			if(!query.toUpperCase().contains("SELECT")) {
				try {
					System.out.println(user);
					con=getStudentConnection();  
					 stmt=con.createStatement();
					int rs = stmt.executeUpdate(query);
					return "Query OK, " + rs + " rows affected";
				} catch (SQLException e) {
					System.out.println("error at queryUpdateRun in StudentQueryHelper + \n" + e);
					return e.getMessage().toString();
				} 
				finally {
				    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
				    try { if (con != null) con.close(); } catch (Exception e) {};
				}
				
			}
			return "Something went wrong";
		}
		
		/**
		 * This just run select querys
		 * @param query:  the query which is going to run on the user's databases
		 * @return  If valid query: a triplet containing true, the resultset rs, and no error message. If not valid query: a triplet containing false, a null resultset, and the error message
		 */
		public triplet querySelectRun(String query) {
			ResultSet rs = null;
			Connection con = null;
			Statement stmt = null;
			
				try {
					con= getStudentConnection(); 
					 stmt=con.createStatement();
					 rs = stmt.executeQuery(query);
					 Grid grid = lh.ResultSetToGrid(rs);
					return new triplet(true, grid, "");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					return new triplet(false, null, e.getMessage().toString());
				}
				finally {
					try { if (rs != null) rs.close(); } catch (Exception e) {};
				    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
				    try { if (con != null) con.close(); } catch (Exception e) {};
				}
			
		}
		
		
		
		public triplet queryDescRun(String query) {
			ResultSet rs = null;
			Connection con = null;
			Statement stmt = null;
			
				try {
					con= getStudentConnection(); 
					 stmt=con.createStatement();
					 rs = stmt.executeQuery(query);
					 Grid grid = lh.ResultSetToGridForDesc(rs);
					return new triplet(true, grid, "");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					return new triplet(false, null, e.getMessage().toString());
				}
				finally {
					try { if (rs != null) rs.close(); } catch (Exception e) {};
				    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
				    try { if (con != null) con.close(); } catch (Exception e) {};
				}
				
			
			
		}
		
		
		
		/**
		 * This run the show table query
		 * @return a Grid that contains all the tables in the users databases.
		 * @throws SQLException if there is a error
		 */
		public Grid queryShowTable()  {
			
			Connection con = null;
			 ResultSet res = null;
			 DatabaseMetaData meta = null;
			
			
			try {
				con=getStudentConnection(); 
				
				Grid<HashMap<String, String>> grid4 = null;
				List<HashMap<String, String>> rows = new ArrayList<>();
				
				 meta = con.getMetaData();
				 res = meta.getTables(null, null, null, 
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
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return null;
			
		}
		

}
