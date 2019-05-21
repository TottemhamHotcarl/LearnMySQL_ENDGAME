package com.example.LearnMySQL_Final;

public class  GroupObject {

	
	static String GROUP_ID;
	 static String GROUP_NAME;
	 static String GROUP_ADMIN;
	static String GROUP_DATABASE;
	
	
	/**
	 * This is a constructor to store information from databases
	 * @param ID: This is the id that is auto increment from the database
	 * @param AdminID: The student number of the student who created the group
	 * @param Name: the name of the group
	 * @param database: the main database the group uses
	 */
	public GroupObject(String ID,String AdminID,String Name,String database) {
		GROUP_ID = ID;
		GROUP_NAME = Name;
		GROUP_ADMIN = AdminID;
		GROUP_DATABASE = database;
	}
	
	/**
	 * This is used to create a groupObject to get the static information
	 */
	public GroupObject() {}
	
	/**
	 * @return The Group_id
	 */
	public String getID() {
		return GROUP_ID;
	}
	/**
	 * @return Group Name
	 */
	public String getName() {
		return GROUP_NAME; 
	}
	/**
	 * @return The groups databases
	 */
	public String getDatabase() {
		return GROUP_DATABASE;
	}
	/**
	 * @return the admin's student number
	 */
	public String getAdmin() {
		return GROUP_ADMIN;
	}
	
	/**
	 * @param ID: The id of the database
	 */
	public void setID(String ID) {
		GROUP_ID = ID;
	}
	
	/**
	 * @param database: The database used by the group
	 */
	public void setDatabase(String database) {
		GROUP_DATABASE = database;
	}
	
	/**
	 * @param Name: The name of the group
	 */
	public void setName(String Name) {
		GROUP_NAME = Name;
	}
	
	/**
	 * @param Admin: The student number of the person who created the group
	 */
	public void setAdmin(String Admin) {
		GROUP_ADMIN = Admin;
	}
	
	
	
	
	
}
