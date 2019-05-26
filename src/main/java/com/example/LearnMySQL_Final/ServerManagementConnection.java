package com.example.LearnMySQL_Final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * This class connects the app to the database
 *
 */
public class ServerManagementConnection {
	Connection con;
	/**
	 * This create a connection to the server database
	 */
	public ServerManagementConnection() {
		try{  
			//Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://146.141.21.143:3306/SERVER","carl","carl");  
		}catch(Exception e){
			System.out.println(e);
			}
	}
	
	/**
	 * @param ip: connects to a different server database
	 */
	public ServerManagementConnection(String ip) {
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
	
	

	/**
	 * Check if it is a students first time loginning
	 * @param Student_no: the student number being check in the database
	 * @return True if it the student first time. False if it not the students first time
	 */
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
	
	/**
	 * Adds a student to the STUDENT TABLE in the databases
	 * @param p: A person object which stores the current user info
	 * @return true if successful saved the student, otherwise false
	 */
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
	/**
	 * This creates the student databases and gives permission to it
	 * @param p: A person object which stores the current user info
	 * @return true if successful saved the student, otherwise false
	 */
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
	
	
	
	/**
	 * Get all the history of a particular student
	 * @param p:A person object which stores the current user info
	 * @return result set containing the students history
	 */ 
	public triplet getStudentHistoryQuery(Person p) {
		ResultSet rs =null;

		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			
			String query2 = "SELECT HISTORY_QUERY,HISTORY_DATE,HISTORY_TIME,HISTORY_ID FROM STUDENT_HISTORY WHERE STUDENT_NO =" + Student_no;
		
			rs = stmt.executeQuery(query2);
			
			return new triplet(true, rs, "");
		}catch (Exception e) {
			System.out.println(e);
			return new triplet(false,rs,e.toString());
					}
	}
	
	/**
	 *  Get all the history of a particular student with a particular phrase in it
	 * @param p: A person object which stores the current user info
	 * @param search: The phrase that is being used to search with
	 * @return If valid query: a triplet containing true, the resultset rs, and no error message. If not valid query: a triplet containing false, a null resultset, and the error message
	 */
	public triplet getStudentHistoryQueryWithSearch(Person p,String search) {
		ResultSet rs =null;
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			
			String query2 = "SELECT HISTORY_QUERY,HISTORY_DATE,HISTORY_TIME,HISTORY_ID FROM STUDENT_HISTORY WHERE STUDENT_NO =" + Student_no + " and HISTORY_QUERY LIKE '%" +search +"%'";

			//System.out.println(query2);

			rs = stmt.executeQuery(query2);
			
			return new triplet(true, rs, "");
		}catch (SQLException e) {
			System.out.println(e);
			return new triplet(false,rs,e.toString());
					}
	}
	
	
	
	
	
	/**
	 * Adds to student History table
	 * @param p: A person object which stores the current user info
	 * @param query: the query being saved
	 * @return true if it was successful, false otherwise
	 */
	public boolean addStudentHistoryQuery(Person p, String query) {
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			

			String query2 = "INSERT INTO STUDENT_HISTORY (STUDENT_NO, HISTORY_QUERY,HISTORY_DATE,HISTORY_TIME)" + "VALUES(?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query2);
			LocalDate now=LocalDate.now();
            LocalTime t=LocalTime.now();
        	String date=now.toString();
        	String time=t.toString();
			
			
			preparedStmt.setString (1, Student_no);
		      preparedStmt.setString (2, query);
		      preparedStmt.setString  (3, date);
		      preparedStmt.setString  (4, time);
			int rs = preparedStmt.executeUpdate();
			
			System.out.println(rs);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
					}
	}
	

	/**
	 * Delete history from HISTORY TABLE
	 * @param HistoryID: The history_id that is stored on the database
	 * @return true if it was successful, false otherwise
	 */
	public boolean deleteHistory(String HistoryID) {
		try {
			Statement stmt=con.createStatement();
			String Query = "DELETE FROM STUDENT_HISTORY WHERE HISTORY_ID = " + HistoryID;
			System.out.println(Query);
			int rs = stmt.executeUpdate(Query);
			if(rs == 1) {
				return true;
			}
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	
	/**
	 * Delete saved_query from SAVED_QUERY Table
	 * @param SAVED_QUERY_ID: the query id that is being deleted
	 * @return true if it was successful, false otherwise
	 */
	public boolean deleteSaved_Query(String SAVED_QUERY_ID) {
		try {
			Statement stmt=con.createStatement();
			String Query = "DELETE FROM SAVED_QUERY WHERE SAVED_QUERY_ID= " +SAVED_QUERY_ID;
			System.out.println(Query);
			int rs = stmt.executeUpdate(Query);
			if(rs == 1) {
				return true;
			}
			return true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	/**
	 * save query in the database
	 * @param p:  A person object which stores the current user info
	 * @param queryName: The name of the query
	 * @param query: The query being saved
	 * @return true if it was successful, false otherwise
	 */
	public boolean addStudentSavedQuery(Person p, String queryName,String query) {
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			
			String query2 = "INSERT INTO SAVED_QUERY (STUDENT_NO, SAVED_QUERY_NAME,SAVED_QUERY)" + "VALUES(?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query2);
			LocalDate now=LocalDate.now();
            LocalTime t=LocalTime.now();
        	String date=now.toString();
        	String time=t.toString();
			
			

			preparedStmt.setString (1, Student_no);
		      preparedStmt.setString (2, queryName);
		      preparedStmt.setString  (3, query);
			int rs = preparedStmt.executeUpdate();
			
			System.out.println(rs);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
					}
	}
	/**
	 * Gets student saved query
	 * @param p:  A person object which stores the current user info
	 * @return If valid query: a triplet containing true, the resultset rs, and no error message. If not valid query: a triplet containing false, a null resultset, and the error message
	 */
	public triplet getStudentSavedQuery(Person p) {
		ResultSet rs =null;
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			

			String query2 = "SELECT SAVED_QUERY, SAVED_QUERY_NAME, STUDENT_NO,SAVED_QUERY_ID FROM SAVED_QUERY WHERE STUDENT_NO =" + Student_no;

			//System.out.println(query2);


			rs = stmt.executeQuery(query2);
			
			return new triplet(true, rs, "");
		}catch (Exception e) {
			System.out.println(e);
			return new triplet(false,rs,e.toString());
					}
	}
	
	

	/**
	 *  Gets student saved query with search term
	 * @param p: A person object which stores the current user info
	 * @param search: the search term
	 * @return If valid query: a triplet containing true, the resultset rs, and no error message. If not valid query: a triplet containing false, a null resultset, and the error message
	 */
	public triplet getStudentSavedQueryWithSearch(Person p,String search) {
		ResultSet rs =null;
		try {
			Statement stmt=con.createStatement();
			String Student_no = p.getId();
			
			
			String query2 = "SELECT SAVED_QUERY, SAVED_QUERY_NAME, STUDENT_NO, SAVED_QUERY_ID FROM SAVED_QUERY WHERE STUDENT_NO = " + Student_no +  " and SAVED_QUERY LIKE '%" +search +"%'";
			System.out.println(query2);
			rs = stmt.executeQuery(query2);
			
			return new triplet(true, rs, "");
		}catch (Exception e) {
			System.out.println(e);
			return new triplet(false,rs,e.toString());
					}
	}
	
	
	

}
