package com.example.LearnMySQL_Final;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;

public class Export_saved_Queries_UI extends Panel implements View  {
	static Button backUI = new Button("Back");
	public Export_saved_Queries_UI(){
setWidth("100%");
		
		setHeight("100%");
		User u = new User();
		ServerManagementConnection smc = new ServerManagementConnection();
		triplet t = smc.getStudentHistoryQuery(u.person);
		
		backUI.setClickShortcut(KeyCode.ESCAPE);
		ResultSet rs2 = t.rs;
		
		
		
		ArrayList<savedObject> ls = new ArrayList<savedObject>();
		try {
			while(rs2.next()) {
				savedObject ho = new savedObject(rs2.getString("SAVED_QUERY"), rs2.getString("SAVED_QUERY_NAME"), rs2.getString("STUDENT_NO"));

				ho.setSavedQuery(rs2.getString("SAVED_QUERY_ID"));
				ls.add(ho);
				
				//System.out.println(rs2.getString("HISTORY_QUERY")+ rs2.getString("HISTORY_DATE")+ rs2.getString("HISTORY_TIME"));
			
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		String s ="Here are all quereis you have ever saved.You can copy to them to your clipboard:\n";
		final HorizontalLayout hl = new HorizontalLayout();
		TextArea area2 = new TextArea("ALL SAVED QUERIES");
		backUI.setClickShortcut(KeyCode.ESCAPE);
		
		int i=0;
		
		
		
		
		while(i<ls.size()) {
			savedObject hot = ls.get(i);
			s= s+hot.getQuery() + ">>>"+ hot.getQueryName()+ "\n";
			++i;
		}
	
		area2.setValue(s);
		area2.setWidth("100%");
		area2.setHeightUndefined();
		hl.addComponent(area2);
		hl.addComponent(backUI);
		hl.setWidth("100%");
		hl.setHeight("100%");
		setContent(hl);
	}

}