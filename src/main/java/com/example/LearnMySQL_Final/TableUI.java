package com.example.LearnMySQL_Final;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.navigator.View;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class TableUI  extends VerticalLayout implements View  {

	public TableUI(ResultSet rs) {
		removeAllComponents();
		setHeight("100%");
		setWidth("100%");
		Grid table = new Grid();
		table.setItems(rs);
		
		try {
			String s = rs.getString("R");
			int id   = rs.getInt(1);
			System.out.println(s + id);
			while(rs.next()) {
				System.out.println(rs);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		addComponent(table);
		
	}
	
}
