package com.example.LearnMySQL_Final;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Export_history_UI extends Panel implements View {
	
	static Button backUI = new Button("Back");
	
	public Export_history_UI(){
		setWidth("100%");
		setHeight("100%");
		User u = new User();
		
		
		backUI.setClickShortcut(KeyCode.ESCAPE);
		// Find the application directory
		String basepath = VaadinService.getCurrent()
		                  .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                        "/WEB-INF/images/back.png"));
		

		
		backUI.setStyleName(ValoTheme.BUTTON_LINK);
		backUI.setIcon(resource);


		
		
		ResultSet rs =null;
		Statement stmt = null;
		Connection con = null;
		ArrayList<HistoryObject> ls = new ArrayList<HistoryObject>();
		
		try {
			con = DriverManager.getConnection(  
					"jdbc:mysql://146.141.21.143:3306/SERVER","carl","carl");
		    stmt=con.createStatement();
			String Student_no = User.person.id;
			
			
			String query2 = "SELECT HISTORY_QUERY,HISTORY_DATE,HISTORY_TIME,HISTORY_ID FROM STUDENT_HISTORY WHERE STUDENT_NO =" + Student_no;
		
			rs = stmt.executeQuery(query2);
			while(rs.next()) {
				HistoryObject ho = new HistoryObject(rs.getString("HISTORY_QUERY"), rs.getString("HISTORY_DATE"), rs.getString("HISTORY_TIME"));
				ho.setHistoryID(rs.getString("HISTORY_ID"));
				ls.add(ho);
			}
			
		}catch (Exception e) {
			System.out.println(e);
			
		}
		finally {
			    try { if (rs != null) rs.close(); } catch (Exception e) {};
			    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
			    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		
				
		
		String s ="";
		
		Label label = new Label("Here are all queries you have ever typed.  You can copy to them to your clipboard:");
		
		label.addStyleName(ValoTheme.LABEL_BOLD);
		label.addStyleName(ValoTheme.LABEL_H2);
		
		
		final VerticalLayout hl = new VerticalLayout();
		TextArea area2 = new TextArea();
	
		area2.addStyleName(ValoTheme.TEXTAREA_BORDERLESS);
		area2.addStyleName(ValoTheme.TEXTAREA_LARGE);
		//area2.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);
		backUI.setClickShortcut(KeyCode.ESCAPE);
		
		int i=0;
		
		
		
		
		while(i<ls.size()) {
			HistoryObject hot = ls.get(i);
			s= s+hot.getQuery() + "           #"+ hot.getDate()+ "   "+ hot.getTime() + "\n" + "\nS";
			++i;
		}
	
		
		hl.setWidth("100%");
		hl.setHeight("100%");
		area2.setValue(s);
		area2.setWidth("100%");
		area2.setHeight("100%");
		hl.addComponent(backUI);
		hl.addComponent(label);
		hl.addComponent(area2);
		hl.setExpandRatio(backUI, .05f);
		hl.setExpandRatio(label, .15f);
		hl.setExpandRatio(area2, 0.84f);
		
		
		setContent(hl);
	
	}

}
