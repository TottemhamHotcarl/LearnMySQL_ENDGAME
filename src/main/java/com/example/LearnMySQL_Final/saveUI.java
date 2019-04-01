package com.example.LearnMySQL_Final;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class saveUI extends VerticalLayout implements View {
	public saveUI() {
		removeAllComponents();
		saveQuery sq = new saveQuery();
		String query = sq.returnQuery();
		TextArea area = new TextArea("Do you want to save this Query?");
		area.setWidth("100%");
		area.setValue(query);
	
		
		final HorizontalLayout hl = new HorizontalLayout();
		TextField searchBox = new TextField("Please enter a name for your query");
		Button save = new Button("Save");
		hl.addComponents(searchBox,save);
		save.addClickListener(e -> {
			if(addToDatabase(area.getValue(),searchBox.getValue())) {
				removeAllComponents();
				addComponent(new TheQueryBox(area.getValue()));
			}
			else {
				Notification.show("Query Name already in use");
			}
			
		
		});
		
		addComponents(area,hl);
		
		
		
	}
	public boolean addToDatabase(String query,String query_name) {
		User u = new User();
		Person p = u.person;
		ServerManagementConnection smc = new ServerManagementConnection();
		if(smc.addStudentHistoryQuery(p, query, query_name)) {
			return true;
		}
		else {
			return false;
		}
	}
}
