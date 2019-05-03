package com.example.LearnMySQL_Final;

import java.util.ArrayList;

public class QueryObject{
	private final String queryNm;
	private final String query;
	private final String Date;
	private final String Time;
	public QueryObject(String query,String Date,String Time,String queryNm) {
		this.query = query;
		this.Date=Date;
		this.Time=Time;
		this.queryNm=queryNm;
	}
	
	public String getQuery() {
		return query;
	}
	public String getDate() {
		return Date;
	}
	public String getTime() {
		return Time;
	}
	public String getQueryName() {
		return queryNm;
	}
	/*@Override
	public boolean equals(Object obj) {
		if(obj instanceof QueryObject) 
			return obj.equals(query);
		return false;
	}
	*/

	
}