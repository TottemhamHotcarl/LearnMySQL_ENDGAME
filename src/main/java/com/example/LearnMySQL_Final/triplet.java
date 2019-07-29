package com.example.LearnMySQL_Final;

import com.vaadin.ui.Grid;

public class triplet {
	public boolean queryOk;
	public Grid grid;
	public String error;
	
	public triplet(boolean ok, Grid rs, String error) {
		queryOk = ok;
		this.grid = rs;
		this.error = error;
	}
	
	

}
