package com.example.LearnMySQL_Final;

import java.sql.ResultSet;

public class triplet {
	boolean queryOk;
	ResultSet rs;
	String error;
	
	public triplet(boolean ok, ResultSet rs, String error) {
		queryOk = ok;
		this.rs = rs;
		this.error = error;
	}

}
