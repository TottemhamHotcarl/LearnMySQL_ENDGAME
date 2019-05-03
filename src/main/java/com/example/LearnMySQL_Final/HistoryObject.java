package com.example.LearnMySQL_Final;

public class HistoryObject {
	
	private final String HISTORY_QUERY;
	private final String HISTORY_DATE;
	private final String HISTORY_TIME;
	String HISTORY_ID;
	public HistoryObject(String query,String Date,String Time) {
		this.HISTORY_QUERY = query;
		this.HISTORY_DATE=Date;
		this.HISTORY_TIME=Time;
	}
	
	public String getQuery() {
		return HISTORY_QUERY;
	}
	public String getDate() {
		return HISTORY_DATE;
	}
	public String getTime() {
		
		return HISTORY_TIME;
	}
	public void setHistoryID(String ID) {
		HISTORY_ID = ID;
	}
	
	public String getHistoryID() {
		return HISTORY_ID;
	}
}
