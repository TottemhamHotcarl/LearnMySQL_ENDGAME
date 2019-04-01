package com.example.LearnMySQL_Final;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class HistoryTab  extends VerticalLayout implements View {

	public HistoryTab() {

		setWidth("100%");
		setHeight("100%");
		TextArea his = new TextArea();
		final HorizontalLayout hl = new HorizontalLayout();
		TextField searchBox = new TextField();
		Button search = new Button("search");
		hl.addComponents(searchBox,search);
		addComponent(hl);
		
		User u = new User();
		Person p = u.person;
		
		ServerManagementConnection smc = new ServerManagementConnection();
		triplet t = smc.getStudentHistoryQuery(p);
		if(t.queryOk) {
			
			ResultSet rs = t.rs;
			ArrayList<History> ls = new ArrayList<History>();
			
			his.setReadOnly(true);
			String out = "";
				try {
					while(rs.next()) {
						out = out + rs.getString("HISTORY_QUERY") +": "+"\n" + rs.getString("HISTORY_QUERY_NAME") + "\n";
					}
					his.setValue(out);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		his.setWidth("100%");
		his.setHeight("100%");
		
		
		search.addClickListener(e->{
			his.setValue("");
			String searchText = searchBox.getValue();
			triplet t2 = smc.getStudentHistoryQueryWithSearch(p, searchText);
			if(t2.queryOk) {
				
				ResultSet rs = t2.rs;
				ArrayList<History> ls = new ArrayList<History>();
				
				his.setReadOnly(true);
				String out = "";
					try {
						while(rs.next()) {
							out = out + rs.getString("HISTORY_QUERY_NAME") +": "+"\n" + rs.getString("HISTORY_QUERY") + "\n";
						}
						his.setValue(out);
						
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
			
		});
		
		
		addComponent(his);
		
		
		
		
		
		}
	
		
		
	
	
}
